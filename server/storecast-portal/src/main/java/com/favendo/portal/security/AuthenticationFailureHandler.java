package com.favendo.portal.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        response.setContentType(APPLICATION_JSON);
        response.setStatus(SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        mapper.writeValue(writer, "Authentication Failed");
        writer.flush();
    }
}
