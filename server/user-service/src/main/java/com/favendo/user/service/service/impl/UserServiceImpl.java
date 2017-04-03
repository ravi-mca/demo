package com.favendo.user.service.service.impl;

import com.favendo.commons.exception.BusinessException;
import com.favendo.user.service.dao.UserDao;
import com.favendo.user.service.domain.User;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User getByUserId(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public User getByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User getByUsernameOrAccountName(String username, String accountName) {
        return userDao.findByUsernameOrAccountName(username, accountName);
    }

    @Override
    public User getByUsernameOrAccountNameAndUserId(String username, String accountName, Long userId) {
        return userDao.findByUsernameOrAccountNameAndUserId(username, accountName, userId);
    }

    public List<User> getAll() throws BusinessException {
        return userDao.findAll();
    }

    @Override
    public User getByAccountNo(String accountNo) throws BusinessException {
        return userDao.findByAccountNo(accountNo);
    }
}
