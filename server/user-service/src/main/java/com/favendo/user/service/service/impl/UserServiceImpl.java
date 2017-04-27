package com.favendo.user.service.service.impl;

import com.favendo.commons.domain.User;
import com.favendo.commons.enums.RoleEnum;
import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.user.service.dao.UserDao;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.user.service.constant.UserConstant.MERCHANT_ID;

import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService {
    
    @Value("${merchant.not.found.error.message}")
    private String merchantNotFoundErrorMessage;
    
    @Value("${invalid.merchant.id.error.message}")
    private String merchantIdNotNullErrorMessage;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User getByUserId(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public User getByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User getByUsernameFirstNameOrAccountName(String username,String firstName,String accountName) {
        return userDao.findByUsernameFirstNameOrAccountName(username,firstName,accountName);
    }

    @Override
    public User getByUsernameFirstNameOrAccountNameAndMerchantId(String username,String firstName,String accountName,
                                                                 Long merchantId) {
        return userDao.findByUsernameFirstNameOrAccountNameAndMerchantId(username,firstName,accountName,merchantId);
    }

    public List<User> getAll() throws BusinessException {
        return userDao.findAll();
    }

    @Override
    public User getByAccountNo(String accountNo) throws BusinessException {
        return userDao.findByAccountNo(accountNo);
    }

    @Override
    @Transactional
    public void deleteById(Long userId) {
        if(Objects.isNull(userId)) {
            throw new IllegalArgumentException(merchantIdNotNullErrorMessage);
        }
        userDao.delete(getAndValidateUserByUserIdAndRole(userId, RoleEnum.MERCHANT.getRole()));
    }
    
    private User getAndValidateUserByUserIdAndRole(Long userId, String role) {
        User user = userDao.findByUserIdAndRole(userId, role);
        if (Objects.isNull(user)) {
            throw new StorecastApiException(BAD_REQUEST, merchantNotFoundErrorMessage, MERCHANT_ID);
        }
        return user;
    }
}
