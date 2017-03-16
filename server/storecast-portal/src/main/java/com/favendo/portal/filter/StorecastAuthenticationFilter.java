package com.favendo.portal.filter;

import com.favendo.user.service.builder.AuthenticationBuilder;
import com.favendo.user.service.jwt.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
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

import static com.favendo.user.service.jwt.TokenConstant.AUTHORIZATION;
import static com.favendo.user.service.jwt.TokenConstant.BEARER;

@Component
public class StorecastAuthenticationFilter extends GenericFilterBean {

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

    private void getAndValidateAuthToken(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String authToken = getAuthToken(httpRequest.getHeader(AUTHORIZATION));
        validateAuthToken(httpResponse, authToken);
    }

    private void validateAuthToken(HttpServletResponse httpResponse, String authToken) throws IOException {
        if (Objects.isNull(authToken)) {
            // AUTHENTICATION_TOKEN_NOT_FOUND
        } else {
            if (!tokenGenerator.validateToken(authToken)) {
                // UNAUTHORIZED_USER
            }
        }
    }

    private String getAuthToken(String authHeader) {
        return Objects.nonNull(authHeader) ?
                authHeader.substring(BEARER.length(), authHeader.length()) :
                null;
    }
}
