package com.favendo.user.service.service;

import com.favendo.user.service.dao.RoleDao;
import com.favendo.user.service.dao.UserDao;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitBean implements InitializingBean {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        //setRole();

        //setUser();
    }

    /*@Transactional(propagation = Propagation.REQUIRES_NEW)
    private void setUser() {
        if (userDao.count() <= 0) {
            User user = new User();
            user.setAccountNo(DEFAULT_ACCOUNT_NO);
            user.setAccountName(DEFAULT_ACCOUNT_NAME);
            user.setFirstName(DEFAULT_FIRSTNAME);
            user.setLastName(DEFAULT_LASTAME);
            user.setPassword(DEFAULT_PASSWORD);
            user.setUsername(DEFAULT_USER_NAME);
            Role role = roleDao.findByName(ROLE_ADMIN);
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            user.setRoles(roles);
            userDao.save(user);
        }
    }

    private void setRole() {
        if (roleDao.count() <= 0) {
            List<Role> roles = new ArrayList<>();
            Role roleAdmin = new Role();
            roleAdmin.setRoleId(1L);
            roleAdmin.setRoleName(ROLE_ADMIN);
            Role roleMerchant = new Role();
            roleMerchant.setRoleId(2l);
            roleMerchant.setRoleName("ROLE_MERCHANT");
            roles.add(roleAdmin);
            roles.add(roleMerchant);
            roleDao.save(roles);

        }
    }*/
}