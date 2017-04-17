package com.favendo.user.ws.rest.builder;

import com.favendo.user.ws.rest.dto.AccessDeniedDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccessDeniedBuilder {

    @Value("${user.access.denied.error.message}")
    private String userAccessDeniedErrorMessage;

    public AccessDeniedDto buildAccessDenied() {
        AccessDeniedDto accessDeniedDto = new AccessDeniedDto();
        accessDeniedDto.setErrorMesesag(userAccessDeniedErrorMessage);
        return accessDeniedDto;
    }
}
