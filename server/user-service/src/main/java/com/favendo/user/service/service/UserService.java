package com.favendo.user.service.service;


import com.favendo.user.service.domain.User;

public interface UserService {

    void save(User user);

    User getByUserId(Long userId);

    User getByUsername(String username);

    User getByUsernameOrAccountName(String username,String accountName);

    User getByUsernameOrAccountNameAndUserId(String username,String accountName,Long userId);
}
