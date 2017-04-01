package com.favendo.portal.filter;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.commons.exception.ErrorKey.UNAUTHORIZED;
import static com.favendo.user.service.constant.MessageConstant.INVALID_USER_TOKEN_ERROR_MESSAGE;
import static com.favendo.user.service.constant.MessageConstant.USER_TOKEN_NOT_FOUND_ERROR_MESSAGE;
import static com.favendo.user.service.jwt.TokenConstant.AUTHORIZATION;
import static com.favendo.user.service.jwt.TokenConstant.BEARER;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.utils.JsonMapper;
import com.favendo.user.service.dto.AuthenticationFailureDto;
import com.favendo.user.service.jwt.TokenUtils;
import com.favendo.user.service.utils.UserContextHolder;

public class AuthenticationFilter extends GenericFilterBean {

    private final UserDetailsService userService;

    public AuthenticationFilter(UserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, BusinessException,
            ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setContentType(APPLICATION_JSON);
        processTokenAuthentication(httpServletRequest, httpServletResponse, getAuthToken(httpServletRequest.getHeader(AUTHORIZATION)));
        chain.doFilter(servletRequest, servletResponse);
    }

    private void processTokenAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String authToken) throws IOException {
        if (Objects.isNull(authToken)) {
            buildAuthenticationFailure(httpServletResponse, USER_TOKEN_NOT_FOUND_ERROR_MESSAGE, BAD_REQUEST.getHttpStatus());
        } else {
            try {
                TokenUtils tokenUtils = new TokenUtils();
                UserDetails userDetails = userService.loadUserByUsername(tokenUtils.getUsernameByToken(authToken));
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                if (BooleanUtils.isFalse(tokenUtils.validateToken(authToken, UserContextHolder.getLoggedInUser()))) {
                    buildAuthenticationFailure(httpServletResponse, INVALID_USER_TOKEN_ERROR_MESSAGE, UNAUTHORIZED.getHttpStatus());
                }
            } catch (Exception exception) {
                buildAuthenticationFailure(httpServletResponse, INVALID_USER_TOKEN_ERROR_MESSAGE, UNAUTHORIZED.getHttpStatus());
            }
        }
    }

    private void buildAuthenticationFailure(HttpServletResponse httpServletResponse, String errorMessage, Integer statusCode) throws IOException {
        AuthenticationFailureDto authenticationFailureDto = new AuthenticationFailureDto();
        httpServletResponse.setStatus(statusCode);
        authenticationFailureDto.setErrorMesesag(errorMessage);
        httpServletResponse.getWriter().write(JsonMapper.objectToJSON(errorMessage));
        httpServletResponse.getWriter().close();
    }

    private String getAuthToken(String authenticationHeader) {
        return Objects.nonNull(authenticationHeader) ?
                authenticationHeader.substring(BEARER.length(), authenticationHeader.length()) :
                null;
    }
}
