package com.favendo.user.service.service;

import com.favendo.commons.enums.RoleEnum;
import com.favendo.user.service.dao.RoleDao;
import com.favendo.user.service.dao.UserDao;
import com.favendo.user.service.domain.Role;
import com.favendo.user.service.domain.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.favendo.user.service.constant.UserConstant.*;

@Component
public class InitBean implements InitializingBean {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        setRole();
        User user = setUser();
        setUserRole(user);
    }

    @Transactional
    private User setUser() {
        if (userDao.count() <= 0) {
            User user = new User();
            user.setAccountNo(DEFAULT_ACCOUNT_NO);
            user.setAccountName(DEFAULT_ACCOUNT_NAME);
            user.setFirstName(DEFAULT_FIRSTNAME);
            user.setLastName(DEFAULT_LASTAME);
            user.setPassword(DEFAULT_PASSWORD);
            user.setUsername(DEFAULT_USER_NAME);
            user = userDao.save(user);
            return user;
        }

        return null;
    }

    @Transactional
    private void setUserRole(User user) {
        if(Objects.nonNull(user)){
            List<Role> roles = new ArrayList<>();
            Role role = roleDao.findByName(RoleEnum.MERCHANT.getRole());
            roles.add(role);
            user.setRoles(roles);
            userDao.save(user);
        }
    }

    @Transactional
    private void setRole() {
        if (roleDao.count() <= 0) {
            List<Role> roles = new ArrayList<>();
            Role roleAdmin = new Role();
            roleAdmin.setRoleId(1L);
            roleAdmin.setRoleName(RoleEnum.ADMIN.getRole());
            Role roleMerchant = new Role();
            roleMerchant.setRoleId(2l);
            roleMerchant.setRoleName(RoleEnum.MERCHANT.getRole());
            roles.add(roleAdmin);
            roles.add(roleMerchant);
            roleDao.save(roles);
        }
    }
}