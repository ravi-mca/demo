package com.favendo.user.service.service;


import com.favendo.commons.domain.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User getByUsername(String username);

    User getByUsernameOrFirstName(String username,String firstName);

    User getByUsernameFirstNameOrAccountName(String username,String accountName,String firstName);
    
    User getByUsernameFirstNameOrAccountNameAndMerchantId(String username,String firstName,String accountName,
                                                          Long merchantId);
}
