package com.favendo.user.service.dao;

import com.favendo.user.service.domain.Role;
import com.favendo.user.service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    @Query("select role from Role role where role.roleName = :roleName")
    Role findByName(@Param("roleName") String roleName);
}
