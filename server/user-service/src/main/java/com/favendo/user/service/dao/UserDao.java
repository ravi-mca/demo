package com.favendo.user.service.dao;

import com.favendo.user.service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

   @Query("select user from User user where user.username = :username")
   User findByUsername(@Param("username") String username);

   @Query("select user from User user where user.username = :username OR user.accountName = :accountName")
   User findByUsernameOrAccountName(@Param("username") String username, @Param("accountName") String accountName);

   @Query("select user from User user where (user.username = :username OR user.accountName = :accountName) AND user.user_id <> :userId")
   User findByUsernameOrAccountNameAndUserId(@Param("username") String username, @Param("accountName")
           String accountName, @Param("userId") Long userId);
}