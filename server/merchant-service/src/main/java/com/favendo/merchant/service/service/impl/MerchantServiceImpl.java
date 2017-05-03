package com.favendo.merchant.service.service.impl;

import com.favendo.commons.domain.Merchant;
import com.favendo.commons.domain.Role;
import com.favendo.commons.domain.User;
import com.favendo.commons.enums.RoleEnum;
import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.merchant.service.dao.MerchantDao;
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
import static com.favendo.commons.exception.ErrorKey.NO_CONTENT;
import static com.favendo.user.service.constant.UserConstant.ACCOUNT_NO;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private MerchantDao merchantDao;

    @Value("${merchant.not.found.by.account.no.error.message}")
    private String merchantNotFoundByAccountNoErrorMessage;

    @Override
    public List<Merchant> getAll() throws BusinessException {
        List<Merchant> merchants = merchantDao.findAll();
        if (CollectionUtils.isEmpty(merchants)) {
            throw new StorecastApiException(NO_CONTENT);
        }
        return merchants;
    }

    @Override
    public Merchant getById(Long merchantId)throws BusinessException{
        return merchantDao.findById(merchantId);
    }

    @Override
    public Merchant getByAccountNo(String accountNo) throws BusinessException {
        Merchant merchant = merchantDao.findByAccountNo(accountNo);
        if (Objects.isNull(merchant)) {
            throw new StorecastApiException(NOT_FOUND, merchantNotFoundByAccountNoErrorMessage, ACCOUNT_NO);
        }
        return merchant;
    }

    @Override
    public Merchant getByAccountName(String accountName)throws BusinessException{
        return merchantDao.findByAccountName(accountName);
    }

    @Override
    @Transactional
    public void save(Merchant merchant,User user) throws BusinessException {
        merchant = merchantDao.save(merchant);
        setRoles(user);
        user.setMerchant(merchant);
        userService.save(user);
    }

    @Override
    @Transactional
    public void update(Merchant merchant,User user) throws BusinessException {
        merchantDao.save(merchant);
        userService.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long merchantId) {
        merchantDao.delete(merchantId);
    }

    @Override
    public Merchant getByAccountNameAndMerchantId(String accountName,Long merchantId)throws BusinessException{
        return merchantDao.findByAccountNameAndMerchantId(accountName,merchantId);
    }

    private User setRoles(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getByName(RoleEnum.MERCHANT.getRole()));
        user.setRoles(roles);
        return user;
    }
}
