package com.favendo.portal.filter;

import com.favendo.user.service.builder.AuthenticationBuilder;
import com.favendo.user.service.jwt.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.commons.exception.ErrorKey.UNAUTHORIZED;
import static com.favendo.user.service.jwt.TokenConstant.AUTHORIZATION;
import static com.favendo.user.service.jwt.TokenConstant.BEARER;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
public class StorecastRequestFilter extends GenericFilterBean {

    @Value("${invalid.user.token.error.message}")
    private String invalidUserTokenErrorMessage;

    @Value("${user.token.not.found.error.message}")
    private String userTokenNotFoundErrorMessage;

    @Autowired
    private AuthenticationBuilder authenticationBuilder;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        getAndValidateAuthToken(request, response);
        chain.doFilter(request, response);
    }

    private void getAndValidateAuthToken(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setContentType(APPLICATION_JSON);
        String authToken = getAuthToken(httpServletRequest.getHeader(AUTHORIZATION));
        validateAuthToken(httpServletResponse, authToken);
    }

    private void validateAuthToken(HttpServletResponse httpServletResponse, String authToken) throws IOException {
        if (Objects.isNull(authToken)) {
            authenticationBuilder.buildAuthenticationFailure(httpServletResponse, userTokenNotFoundErrorMessage,
                    BAD_REQUEST.getHttpStatus());
        } else {
            try {
                if (!tokenGenerator.validateToken(authToken)) {
                    authenticationBuilder.buildAuthenticationFailure(httpServletResponse, invalidUserTokenErrorMessage,
                            UNAUTHORIZED.getHttpStatus());
                }
            } catch (Exception exception) {
                authenticationBuilder.buildAuthenticationFailure(httpServletResponse, invalidUserTokenErrorMessage,
                        UNAUTHORIZED.getHttpStatus());
            }
        }
    }

    private String getAuthToken(String authenticationHeader) {
        return Objects.nonNull(authenticationHeader) ?
                authenticationHeader.substring(BEARER.length(), authenticationHeader.length()) :
                null;
    }
}
