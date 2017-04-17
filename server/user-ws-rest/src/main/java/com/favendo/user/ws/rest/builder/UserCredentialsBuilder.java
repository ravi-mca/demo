package com.favendo.user.ws.rest.builder;

import com.favendo.commons.domain.User;
import com.favendo.user.ws.rest.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialsBuilder {

    public UserDto buildUserCredentials(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}
