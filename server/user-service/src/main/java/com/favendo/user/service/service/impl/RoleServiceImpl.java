package com.favendo.user.service.service.impl;

import com.favendo.user.service.dao.RoleDao;
import com.favendo.user.service.domain.Role;
import com.favendo.user.service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getByName(String roleName) {
        return roleDao.findByName(roleName);
    }
}
