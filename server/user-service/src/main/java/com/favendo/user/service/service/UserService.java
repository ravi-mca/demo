package com.favendo.user.service.service;


import com.favendo.commons.domain.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User getByUsername(String username);

    List<User> getByUsernameAndUserId(String username,Long userId);
}
