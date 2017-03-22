package com.favendo.user.service.service.impl;

import com.favendo.user.service.dao.UserDao;
import com.favendo.user.service.domain.User;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
