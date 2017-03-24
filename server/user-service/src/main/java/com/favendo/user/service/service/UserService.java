package com.favendo.user.service.service;


import java.util.List;

import com.favendo.user.service.domain.User;

public interface UserService {

    User getUserByUsername(String username);
    List<User> getListOfMerchants();
    User getUserByAccountNo(String accountNo);
}
