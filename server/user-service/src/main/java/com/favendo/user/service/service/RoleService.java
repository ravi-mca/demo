package com.favendo.user.service.service;


import com.favendo.user.service.domain.Role;

public interface RoleService {

    Role getByName(String roleName);
}
