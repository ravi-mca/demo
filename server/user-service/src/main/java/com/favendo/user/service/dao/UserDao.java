package com.favendo.user.service.dao;

import com.favendo.commons.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query("select user from User user where user.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("select user from User as user join user.roles as role where role.roleName = 'ROLE_MERCHANT' ORDER BY user.firstName ASC")
    List<User> findAll();

    @Query("select user from User user where user.accountNo = :accountNo")
    User findByAccountNo(@Param("accountNo") String accountNo);

    @Query("select user " +
            "from User user " +
            "where upper(user.username) = upper(:username) OR " +
            "upper(user.accountName) = upper(:accountName) OR " +
            "upper(user.firstName) = upper(:firstName) ")
    User findByUsernameOrAccountName(@Param("username") String username, @Param("accountName") String accountName,
                                     @Param("firstName") String firstName);

    @Query("select user " +
            "from User user " +
            "where ( upper(user.username) = upper(:username) OR " +
            "upper(user.firstName) = upper(:firstName) OR " +
            "upper(user.accountName) = upper(:accountName)) AND " +
            "user.user_id <> :userId")
    User findByUsernameOrAccountNameAndUserId(@Param("username") String username, @Param("accountName")
            String accountName, @Param("userId") Long userId, @Param("firstName") String firstName);
}
