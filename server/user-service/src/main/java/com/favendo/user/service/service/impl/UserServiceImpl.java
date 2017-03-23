package com.favendo.user.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favendo.commons.exception.BusinessException;
import com.favendo.user.service.dao.UserDao;
import com.favendo.user.service.domain.User;
import com.favendo.user.service.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> getListOfMerchants() throws BusinessException {
        List<User> users = userDao.getListOfMerchants();
        return users;
    }
}
