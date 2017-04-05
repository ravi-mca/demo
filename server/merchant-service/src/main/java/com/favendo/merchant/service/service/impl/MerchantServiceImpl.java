package com.favendo.merchant.service.service.impl;

import com.favendo.commons.enums.RoleEnum;
import com.favendo.commons.exception.BusinessException;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.merchant.service.builder.MerchantBuilder;
import com.favendo.merchant.service.service.MerchantService;
import com.favendo.merchant.service.validator.MerchantValidator;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.user.service.domain.User;
import com.favendo.user.service.service.RoleService;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.commons.exception.ErrorKey.NOT_FOUND;
import static com.favendo.user.service.constant.UserConstant.ACCOUNT_NO;
import static com.favendo.user.service.constant.UserConstant.MERCHANT_ID;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private MerchantBuilder merchantBuilder;

    @Autowired
    private MerchantValidator merchantValidator;

    @Value("${merchant.not.found.error.message}")
    private String merchantNotFoundErrorMessage;

    @Value("${merchant.not.found.by.account.no.error.message}")
    private String merchantNotFoundByAccountNoErrorMessage;

    @Override
    @Transactional
    public void save(MerchantDto merchantDto) throws BusinessException {
        merchantValidator.validateRequest(merchantDto);
        merchantValidator.validateDuplication(merchantDto, userService.getByUsernameOrAccountName(merchantDto
                .getEmail(), merchantDto.getAccountName()));
        userService.save(merchantBuilder.buildMerchant(merchantDto, roleService.getByName(RoleEnum.MERCHANT.getRole())));
    }

    @Override
    @Transactional
    public void update(MerchantDto merchantDto, Long merchantId) {
        User user = getAndValidateUserByMerchantId(merchantId);
        merchantValidator.validateContactDetails(merchantDto.getEmail(), merchantDto.getPhone());
        merchantValidator.validateDuplication(merchantDto, userService.getByUsernameOrAccountNameAndUserId(merchantDto
                .getEmail(), merchantDto.getAccountName(), user.getUser_id()));
        userService.save(merchantBuilder.buildMerchant(merchantDto, user));
    }

    @Override
    public List<User> getAll() {
        List<User> merchants = userService.getAll();
        if (CollectionUtils.isEmpty(merchants)) {
            throw new StorecastApiException(NOT_FOUND);
        }
        return merchants;
    }

    @Override
    public User getByAccountNo(String accountNo) {
        merchantValidator.validateAccountNo(accountNo);
        User merchant = userService.getByAccountNo(accountNo);
        if (Objects.isNull(merchant)) {
            throw new StorecastApiException(NOT_FOUND, merchantNotFoundByAccountNoErrorMessage, ACCOUNT_NO);
        }
        return merchant;
    }

    private User getAndValidateUserByMerchantId(Long merchantId) {
        User user = userService.getByUserId(merchantId);
        if (Objects.isNull(user)) {
            throw new StorecastApiException(BAD_REQUEST, merchantNotFoundErrorMessage, MERCHANT_ID);
        }
        return user;
    }
}
