package com.favendo.user.service.service;


import com.favendo.commons.domain.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User getByUsername(String username);

    List<User> getByUsernameOrFirstNameOrCustomerName(String username,String firstName,String name);

    List<User> getByUsernameFirstNameOrAccountName(String username,String accountName,String firstName);

    List<User> getByUsernameFirstNameOrAccountNameAndMerchantId(String username,String firstName,String accountName, Long merchantId);
    
    List<User> findByUsernameOrNameAndCustomerId(String username, String name, Long customerId);
}
