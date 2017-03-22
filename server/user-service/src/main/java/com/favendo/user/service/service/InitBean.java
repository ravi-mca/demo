package com.favendo.user.service.service;

import static com.favendo.user.service.constant.StorecastUserConstant.*;

import java.util.Arrays;

import com.favendo.user.service.domain.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.favendo.user.service.dao.UserDao;
import com.favendo.user.service.domain.Role;

@Component
public class InitBean implements InitializingBean {

    @Autowired
    private UserDao userDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (userDao.count() <= 0 ) {
            User user = new User();
            user.setAccountName(DEFAULT_ACCOUNT_NAME);
            user.setFirstName(DEFAULT_FIRSTNAME);
            user.setLastName(DEFAULT_LASTAME);
            user.setPassword(DEFAULT_PASSWORD);
            user.setUsername(DEFAULT_USER_NAME);
            Role role = new Role();
            role.setRoleId(1l);
            role.setRoleName(DEFAULT_ROLE_NAME);
            user.setRoles(Arrays.asList(role));
            userDao.save(user);
        }
    }
}