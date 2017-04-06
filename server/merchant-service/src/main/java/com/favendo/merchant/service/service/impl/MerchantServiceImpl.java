package com.favendo.merchant.service.service.impl;

import com.favendo.commons.domain.Role;
import com.favendo.commons.domain.User;
import com.favendo.commons.enums.RoleEnum;
import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.merchant.service.service.MerchantService;
import com.favendo.user.service.service.RoleService;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.favendo.commons.exception.ErrorKey.NOT_FOUND;
import static com.favendo.user.service.constant.UserConstant.ACCOUNT_NO;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Value("${merchant.not.found.by.account.no.error.message}")
    private String merchantNotFoundByAccountNoErrorMessage;

    @Override
    public List<User> getAll() throws BusinessException {
        List<User> merchants = userService.getAll();
        if (CollectionUtils.isEmpty(merchants)) {
            throw new StorecastApiException(NOT_FOUND);
        }
        return merchants;
    }

    @Override
    public User getByAccountNo(String accountNo) throws BusinessException {
        User merchant = userService.getByAccountNo(accountNo);
        if (Objects.isNull(merchant)) {
            throw new StorecastApiException(NOT_FOUND, merchantNotFoundByAccountNoErrorMessage, ACCOUNT_NO);
        }
        return merchant;
    }

    @Override
    @Transactional
    public void save(User merchant) throws BusinessException {
        userService.save(setRoles(merchant));
    }

    @Override
    @Transactional
    public void update(User merchant) throws BusinessException {
        userService.save(merchant);
    }

    private User setRoles(User merchant) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getByName(RoleEnum.MERCHANT.getRole()));
        merchant.setRoles(roles);
        return merchant;
    }
}
