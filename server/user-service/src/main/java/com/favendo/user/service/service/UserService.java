package com.favendo.user.service.service;


import com.favendo.user.service.domain.User;

public interface UserService {

    User getByUsername(String username);

    User getByUsernameOrAccountName(String username,String accountName);

    void save(User user);
}
