package com.favendo.user.service.service;


import java.util.List;

import com.favendo.user.service.domain.User;

public interface UserService {
    
    List<User> getAll();

    void save(User user);

    User getByUserId(Long userId);

    User getByUsername(String username);

    User getByUsernameOrAccountName(String username,String accountName);

    User getByAccountNo(String accountNo);
    
    User getByUsernameOrAccountNameAndUserId(String username,String accountName,Long userId);
}
