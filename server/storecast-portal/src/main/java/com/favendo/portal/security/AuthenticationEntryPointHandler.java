package com.favendo.portal.security;

import com.favendo.commons.utils.JsonMapper;
import com.favendo.user.service.dto.AuthenticationFailureDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.favendo.commons.exception.ErrorKey.UNAUTHORIZED;

@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Value("${invalid.user.token.error.message}")
    private String invalidUserTokenErrorMessage;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception)
            throws IOException {
        AuthenticationFailureDto authenticationFailureDto = new AuthenticationFailureDto();
        httpServletResponse.setStatus(UNAUTHORIZED.getHttpStatus());
        authenticationFailureDto.setErrorMesesag(null);
        httpServletResponse.getWriter().write(JsonMapper.objectToJSON(invalidUserTokenErrorMessage));
        httpServletResponse.getWriter().close();
    }
}
