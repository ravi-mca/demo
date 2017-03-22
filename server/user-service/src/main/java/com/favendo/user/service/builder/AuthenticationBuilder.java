package com.favendo.user.service.builder;

import com.favendo.commons.utils.JsonMapper;
import com.favendo.user.service.domain.Role;
import com.favendo.user.service.dto.AuthenticationFailureDto;
import com.favendo.user.service.dto.AuthenticationSuccessDto;
import com.favendo.user.service.dto.RoleDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationBuilder {

    public AuthenticationSuccessDto buildAuthenticationSuccess(List<Role> roles, String token) {
        AuthenticationSuccessDto authenticationSuccessDTO = new AuthenticationSuccessDto();
        authenticationSuccessDTO.setAccessToken(token);
        authenticationSuccessDTO.setRoles(roles.stream().map(this::buildRoles).collect(Collectors.toList()));
        return authenticationSuccessDTO;
    }

    public AuthenticationFailureDto buildAuthenticationFailure(String errorMessage) {
        AuthenticationFailureDto authenticationFailureDto = new AuthenticationFailureDto();
        authenticationFailureDto.setErrorMesesag(errorMessage);
        return authenticationFailureDto;
    }

    public void buildAuthenticationFailure(HttpServletResponse httpServletResponse, String errorMessage, Integer statusCode) throws IOException {
        httpServletResponse.setStatus(statusCode);
        httpServletResponse.getWriter().write(JsonMapper.objectToJSON(buildAuthenticationFailure(errorMessage)));
    }

    private RoleDto buildRoles(Role roles) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRole(roles.getRoleName());
        return roleDto;
    }
}
