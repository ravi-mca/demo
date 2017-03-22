package com.favendo.user.service.service.impl;

import com.favendo.user.service.dao.UserDao;
import com.favendo.user.service.domain.User;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User getByUsernameOrAccountName(String username,String accountName) {
        return userDao.findByUsernameOrAccountName(username,accountName);
    }

    @Override
    public void save(User user){
        userDao.save(user);
    }
}
