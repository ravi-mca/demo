package com.favendo.user.service.builder;

import com.favendo.user.service.domain.StorecastUser;
import com.favendo.user.service.dto.AuthenticationSuccessDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationBuilder {

    public AuthenticationSuccessDTO buildAuthenticationSuccess(String token) {
        AuthenticationSuccessDTO authenticationSuccessDTO = new AuthenticationSuccessDTO();
        authenticationSuccessDTO.setAccessToken(token);
        return authenticationSuccessDTO;
    }

}
