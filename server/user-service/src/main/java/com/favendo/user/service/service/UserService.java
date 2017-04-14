package com.favendo.user.service.service;


import com.favendo.commons.domain.User;

import java.util.List;

public interface UserService {
    
    List<User> getAll();

    void save(User user);

    User getByUserId(Long userId);

    User getByAccountNo(String accountNo);

    User getByUsername(String username);

    User getByUsernameOrAccountName(String username,String accountName,String firstName);
    
    User getByUsernameOrAccountNameAndUserId(String username,String accountName,Long userId,String firstName);
    
    void deleteById(Long userId);
}
