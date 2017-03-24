package com.favendo.user.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favendo.user.service.domain.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query("select user from User user where user.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("select user from User user where user.username = :username OR user.accountName = :accountName")
    User findByUsernameOrAccountName(@Param("username") String username, @Param("accountName") String accountName);
    
    @Query("select user from User as user join user.roles as role where role.roleName = 'ROLE_MERCHANT' ORDER BY user.firstName ASC")
    List<User> getListOfMerchants();
    
    @Query("select user from User user where user.accountNo = :accountNo")
    User findByAccountNo(@Param("accountNo") String accountNo);
}
