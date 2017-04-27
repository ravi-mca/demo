package com.favendo.user.service.service;


import com.favendo.commons.domain.User;

import java.util.List;

public interface UserService {
    
    List<User> getAll();

    void save(User user);

    User getByUserId(Long userId);

    User getByAccountNo(String accountNo);

    User getByUsername(String username);

    User getByUsernameFirstNameOrAccountName(String username,String accountName,String firstName);
    
    User getByUsernameFirstNameOrAccountNameAndMerchantId(String username,String firstName,String accountName,
                                                          Long merchantId);
    
    void deleteById(Long userId);
}
