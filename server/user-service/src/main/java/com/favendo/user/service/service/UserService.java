package com.favendo.user.service.service;


import com.favendo.user.service.domain.User;

public interface UserService {

    User getUserByUsername(String username);
}
