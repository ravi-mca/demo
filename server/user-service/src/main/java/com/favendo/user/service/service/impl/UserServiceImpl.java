package com.favendo.user.service.service.impl;

import com.favendo.commons.domain.User;
import com.favendo.user.service.dao.UserDao;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Value("${merchant.not.found.error.message}")
    private String merchantNotFoundErrorMessage;

    @Value("${invalid.merchant.id.error.message}")
    private String merchantIdNotNullErrorMessage;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> getByUsernameOrFirstNameOrCustomerName(String username, String firstName, String name) {
        return userDao.findByUsernameOrFirstNameOrCustomerName(username, firstName,name);
    }

    @Override
    public List<User> getByUsernameFirstNameOrAccountName(String username, String firstName, String accountName) {
        return userDao.findByUsernameFirstNameOrAccountName(username, firstName, accountName);
    }

    @Override
    public List<User> getByUsernameFirstNameOrAccountNameAndMerchantId(String username, String firstName, String accountName, Long merchantId) {
        return userDao.findByUsernameFirstNameOrAccountNameAndMerchantId(username, firstName, accountName, merchantId);
    }
    
    @Override
    public List<User> findByUsernameOrNameAndCustomerId(String username, String name, Long customerId) {
        return userDao.findByUsernameOrNameAndCustomerId(username, name, customerId);
    }
}
