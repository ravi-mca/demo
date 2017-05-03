package com.favendo.user.service.service;


import com.favendo.commons.domain.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User getByUsername(String username);

    List<User> getByUsernameAndUserId(String username,Long userId);

    List<User> getByUsernameOrFirstNameOrCustomerName(String username,String firstName,String name);
    
    List<User> findByUsernameOrNameAndCustomerId(String username, String name, Long customerId);
}
