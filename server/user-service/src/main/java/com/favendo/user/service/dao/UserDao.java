package com.favendo.user.service.dao;

import com.favendo.commons.domain.Merchant;
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

    @Query("select user from User user where user.username = :username")
    User findByAccountNo(@Param("username") String username);

    @Query("select user from User as user join user.roles as role where user.user_id =:userId and role.roleName = :role")
    User findByUserIdAndRole(@Param("userId") Long userId, @Param("role") String role);

    @Query("select user " +
            "from User user join fetch user.customer as customer " +
            "where  user.customer.customerId = customer.customerId AND  " +
            "upper(user.username) = upper(:username) OR " +
            "upper(user.firstName) = upper(:firstName) OR " +
            "upper(user.customer.name) = upper(:name) ")
    List<User> findByUsernameOrFirstNameOrCustomerName(@Param("username") String username,
                                                 @Param("firstName") String firstName,
                                                 @Param("name") String name);

    @Query("select user " +
            "from User user join fetch user.merchant as merchant " +
            "where  user.merchant.merchantId = merchant.merchantId AND  " +
            "upper(user.username) = upper(:username) OR " +
            "upper(user.firstName) = upper(:firstName) OR " +
            "upper(merchant.accountName) = upper(:accountName) ")
    List<User> findByUsernameFirstNameOrAccountName(@Param("username") String username,
                                                  @Param("firstName") String firstName,
                                                  @Param("accountName") String accountName);

    @Query("select user " +
            "from User user join fetch user.merchant as merchant " +
            "where  user.merchant.merchantId = merchant.merchantId AND  " +
            "upper(user.username) = upper(:username) OR " +
            "upper(user.firstName) = upper(:firstName) OR " +
            "upper(merchant.accountName) = upper(:accountName) AND " +
            "user.merchant.merchantId <> :merchantId")
    List<User> findByUsernameFirstNameOrAccountNameAndMerchantId(@Param("username") String username,
                                                           @Param("firstName") String firstName,
                                                           @Param("accountName") String accountName,
                                                           @Param("merchantId") Long merchantId);
}
