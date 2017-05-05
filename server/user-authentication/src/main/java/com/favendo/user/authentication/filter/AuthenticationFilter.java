package com.favendo.user.authentication.filter;

import com.favendo.commons.utils.JsonMapper;
import com.favendo.user.service.jwt.TokenUtils;
import com.favendo.user.service.utils.UserContextHolder;
import com.favendo.user.ws.rest.dto.AuthenticationFailureDto;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Objects;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.user.service.constant.MessageConstant.INVALID_USER_TOKEN_ERROR_MESSAGE;
import static com.favendo.user.service.constant.MessageConstant.USER_TOKEN_NOT_FOUND_ERROR_MESSAGE;
import static com.favendo.user.service.jwt.TokenConstant.AUTHORIZATION;
import static com.favendo.user.service.jwt.TokenConstant.BEARER;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final UserDetailsService userService;

    public AuthenticationFilter(UserDetailsService userService, RequestMatcher matcher) {
        super(matcher);
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String authToken = getAuthToken(httpServletRequest.getHeader(AUTHORIZATION));
        if (Objects.isNull(authToken)) {
            httpServletResponse.setStatus(BAD_REQUEST.getHttpStatus());
            throw new AuthenticationCredentialsNotFoundException(USER_TOKEN_NOT_FOUND_ERROR_MESSAGE);
        } else {
            try {
                TokenUtils tokenUtils = new TokenUtils();
                if (BooleanUtils.isFalse(tokenUtils.validateToken(authToken, UserContextHolder.getLoggedInUser()))) {
                    throw new AuthenticationCredentialsNotFoundException(INVALID_USER_TOKEN_ERROR_MESSAGE);
                }
                return getAuthenticationByAuthToken(authToken, httpServletRequest);
            } catch (Exception exception) {
                throw new AuthenticationCredentialsNotFoundException(INVALID_USER_TOKEN_ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authResult);
        SecurityContextHolder.setContext(securityContext);
        chain.doFilter(request, response);
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                           AuthenticationException authenticationException) throws IOException, ServletException {
        AuthenticationFailureDto authenticationFailureDto = new AuthenticationFailureDto();
        authenticationFailureDto.setErrorMesesag(authenticationException.getMessage());
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON);
        httpServletResponse.getWriter().append(JsonMapper.objectToJSON(authenticationFailureDto));
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByAuthToken(String authToken, HttpServletRequest httpServletRequest) throws Exception {
        TokenUtils tokenUtils = new TokenUtils();
        UserDetails userDetails = userService.loadUserByUsername(tokenUtils.getUsernameByToken(authToken));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    private String getAuthToken(String authenticationHeader) {
        return Objects.nonNull(authenticationHeader) ?
                authenticationHeader.substring(BEARER.length(), authenticationHeader.length()) :
                null;
    }
}
