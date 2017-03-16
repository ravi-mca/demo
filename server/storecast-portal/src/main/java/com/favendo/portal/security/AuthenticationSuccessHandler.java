package com.favendo.portal.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.favendo.user.service.domain.StorecastUser;
import com.favendo.user.service.domain.StorecastUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setStatus(SC_OK);
        response.setContentType(APPLICATION_JSON);

        StorecastUserDetails userDetails = (StorecastUserDetails) authentication.getPrincipal();
        StorecastUser storecastUser = userDetails.getStorecastUser();
        userDetails.setStorecastUser(storecastUser);

        PrintWriter writer = response.getWriter();
        mapper.writeValue(writer, storecastUser);
        writer.flush();
    }
}
