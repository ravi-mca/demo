package com.favendo.merchant.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.favendo.commons.exception.BusinessException;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.merchant.service.helper.MerchantHelper;
import com.favendo.merchant.service.service.MerchantService;
import com.favendo.merchant.service.validator.MerchantValidator;
import com.favendo.user.service.dao.UserDao;
import com.favendo.user.service.domain.User;
import com.favendo.user.service.service.UserService;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private MerchantHelper merchantHelper;

    @Autowired
    private MerchantValidator merchantValidator;

    @Override
    @Transactional
    public void save(MerchantDto merchantDto) throws BusinessException {
        merchantValidator.validateMerchantRequest(merchantDto);
        merchantValidator.validateDuplicateMerchant(merchantDto, userDao.findByUsernameOrAccountName(merchantDto
                .getEmail(), merchantDto.getAccountName()));
        userDao.save(merchantHelper.buildMerchant(merchantDto));
    }

    @Override
    public List<User> getListOFMerchants() throws BusinessException {
        List<User> merchants = userService.getListOfMerchants(); 
        return merchants;
    }
}
