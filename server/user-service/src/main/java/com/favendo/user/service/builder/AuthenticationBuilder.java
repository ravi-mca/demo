package com.favendo.user.service.builder;

import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.utils.JsonMapper;
import com.favendo.user.service.dto.AuthenticationFailureDto;
import com.favendo.user.service.dto.AuthenticationSuccessDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationBuilder {

    public AuthenticationSuccessDto buildAuthenticationSuccess(String token) {
        AuthenticationSuccessDto authenticationSuccessDTO = new AuthenticationSuccessDto();
        authenticationSuccessDTO.setAccessToken(token);
        return authenticationSuccessDTO;
    }

    public AuthenticationFailureDto buildAuthenticationFailure(String errorMessage) {
        AuthenticationFailureDto authenticationFailureDto = new AuthenticationFailureDto();
        authenticationFailureDto.setErrorMesesag(errorMessage);
        return authenticationFailureDto;
    }

    public void buildAuthenticationFailure(HttpServletResponse httpServletResponse, String errorMessage,Integer statusCode) throws IOException {
        httpServletResponse.setStatus(statusCode);
        httpServletResponse.getWriter().write(JsonMapper.objectToJSON(buildAuthenticationFailure(errorMessage)));
    }
}
