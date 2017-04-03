package com.favendo.merchant.service.service.impl;

import com.favendo.commons.exception.BusinessException;
import com.favendo.merchant.service.service.MerchantService;
import com.favendo.user.service.domain.User;
import com.favendo.user.service.service.RoleService;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private UserService userService;

    @Override
    public void save(User merchant) throws BusinessException {
        userService.save(merchant);
    }

    @Override
    public List<User> getAll() throws BusinessException {
        return userService.getAll();
    }

    @Override
    public User getByAccountNo(String accountNo) throws BusinessException {
        return userService.getByAccountNo(accountNo);
    }
}
