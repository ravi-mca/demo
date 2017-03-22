package com.favendo.merchant.service.service.impl;

import com.favendo.commons.enums.RoleEnum;
import com.favendo.commons.exception.BusinessException;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.merchant.service.helper.MerchantHelper;
import com.favendo.merchant.service.service.MerchantService;
import com.favendo.merchant.service.validator.MerchantValidator;
import com.favendo.user.service.service.RoleService;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MerchantHelper merchantHelper;

    @Autowired
    private MerchantValidator merchantValidator;

    @Override
    @Transactional
    public void save(MerchantDto merchantDto) throws BusinessException {
        merchantValidator.validateMerchantRequest(merchantDto);
        merchantValidator.validateDuplicateMerchant(merchantDto, userService.getByUsernameOrAccountName(merchantDto
                .getEmail(), merchantDto.getAccountName()));
        userService.save(merchantHelper.buildMerchant(merchantDto,roleService.getByName(RoleEnum.MERCHANT.getRole())));
    }
}
