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

    public List<User> getByUsernameAndUserId(String username, Long userId) {
        return userDao.findByUsernameAndUserId(username, userId);
    }
}
