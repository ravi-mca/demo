package com.favendo.merchant.service.service.impl;

import com.favendo.commons.enums.RoleEnum;
import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.merchant.service.helper.MerchantHelper;
import com.favendo.merchant.service.service.MerchantService;
import com.favendo.merchant.service.validator.MerchantValidator;
import com.favendo.user.service.domain.User;
import com.favendo.user.service.service.RoleService;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.favendo.user.service.constant.UserConstant.MERCHANT_ID;

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

    @Value("${merchant.not.found.error.message}")
    private String merchantNotFoundErrorMessage;

    @Override
    @Transactional
    public void save(MerchantDto merchantDto) throws BusinessException {
        merchantValidator.validateRequest(merchantDto);
        merchantValidator.validateDuplication(merchantDto, userService.getByUsernameOrAccountName(merchantDto
                .getEmail(), merchantDto.getAccountName()));
        userService.save(merchantHelper.buildMerchant(merchantDto, roleService.getByName(RoleEnum.MERCHANT.getRole())));
    }

    @Override
    @Transactional
    public void update(MerchantDto merchantDto, Long merchantId) throws BusinessException {
        User user = getAndValidateUserByMerchantId(merchantId);
        merchantValidator.validateContactDetails(merchantDto.getEmail(), merchantDto.getPhone());
        merchantValidator.validateDuplication(merchantDto, userService.getByUsernameOrAccountNameAndUserId(merchantDto
                .getEmail(), merchantDto.getAccountName(), user.getUser_id()));
        userService.save(merchantHelper.buildMerchant(merchantDto, user));
    }

    private User getAndValidateUserByMerchantId(Long merchantId) {
        User user = userService.getByUserId(merchantId);
        if (Objects.isNull(user)) {
            throw new StorecastApiException(ErrorKey.BAD_REQUEST, merchantNotFoundErrorMessage, MERCHANT_ID);
        }
        return user;
    }
}
