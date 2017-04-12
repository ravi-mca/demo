package com.favendo.user.ws.rest.validator;

import com.favendo.commons.domain.Role;
import com.favendo.commons.domain.User;
import com.favendo.commons.enums.RoleEnum;
import com.favendo.commons.validator.EmptyOrNullValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;

@Component
public class UserValidator {

    @Autowired
    private EmptyOrNullValidator emptyOrNullValidator;

    @Value("${invalid.user.role.error.message}")
    private String invalidUserRoleErrorMessage;

    public void validateUserRole(User user){
        emptyOrNullValidator.validateFieldIfNull(getRoleIfAdmin(user),BAD_REQUEST,invalidUserRoleErrorMessage);
    }

    private Role getRoleIfAdmin(User user) {
        return  user.getRoles().stream()
                .filter(role -> RoleEnum.ADMIN.getRole().equals(role.getRoleName()))
                .findAny()
                .orElse(null);
    }
}
