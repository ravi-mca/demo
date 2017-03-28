package com.favendo.user.service.builder;

import com.favendo.user.service.dto.AccessDeniedDto;
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
