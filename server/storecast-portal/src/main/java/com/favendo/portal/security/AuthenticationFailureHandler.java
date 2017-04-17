package com.favendo.portal.security;

import com.favendo.commons.utils.JsonMapper;
import com.favendo.user.ws.rest.builder.AuthenticationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;


@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Value("${invalid.user.credential.error.message}")
    private String invalidUserCredentialErrorMessage;

    @Autowired
    private AuthenticationBuilder authenticationBuilder;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException exception) throws IOException, ServletException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.setStatus(UNAUTHORIZED.getStatusCode());
        httpServletResponse.getWriter().write(JsonMapper.objectToJSON((authenticationBuilder
                .buildAuthenticationFailure(invalidUserCredentialErrorMessage))));
    }
}
