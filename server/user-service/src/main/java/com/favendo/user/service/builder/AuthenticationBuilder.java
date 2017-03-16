package com.favendo.user.service.builder;

import com.favendo.user.service.dto.AuthenticationFailureDto;
import com.favendo.user.service.dto.AuthenticationSuccessDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationBuilder {

    @Value("${invalid.user.credential.error.message}")
    private String invalidUserCredentialErrorMessage;

    public AuthenticationSuccessDto buildAuthenticationSuccess(String token) {
        AuthenticationSuccessDto authenticationSuccessDTO = new AuthenticationSuccessDto();
        authenticationSuccessDTO.setAccessToken(token);
        return authenticationSuccessDTO;
    }

    public AuthenticationFailureDto buildAuthenticationFailure(){
        AuthenticationFailureDto authenticationFailureDto = new AuthenticationFailureDto();
        authenticationFailureDto.setErrorMesesag(invalidUserCredentialErrorMessage);
        return authenticationFailureDto;
    }
}
