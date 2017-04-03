package com.favendo.merchant.ws.rest.helper;

import com.favendo.commons.enums.RoleEnum;
import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.exception.StorecastApiException;
import com.favendo.merchant.service.service.MerchantService;
import com.favendo.merchant.ws.rest.builder.MerchantBuilder;
import com.favendo.merchant.ws.rest.convertor.MerchantDtoConverter;
import com.favendo.merchant.ws.rest.dto.MerchantDto;
import com.favendo.merchant.ws.rest.validator.MerchantValidator;
import com.favendo.user.service.domain.User;
import com.favendo.user.service.service.RoleService;
import com.favendo.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

import static com.favendo.commons.exception.ErrorKey.NOT_FOUND;
import static com.favendo.user.service.constant.UserConstant.ACCOUNT_NO;
import static com.favendo.user.service.constant.UserConstant.MERCHANT_ID;

@Component
public class MerchantHelper {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantBuilder merchantBuilder;

    @Autowired
    private MerchantValidator merchantValidator;

    @Autowired
    private MerchantDtoConverter merchantDtoConverter;

    @Value("${merchant.not.found.error.message}")
    private String merchantNotFoundErrorMessage;

    @Value("${merchant.not.found.by.account.no.error.message}")
    private String merchantNotFoundByAccountNoErrorMessage;

    @Transactional
    public void save(MerchantDto merchantDto) {
        merchantValidator.validateRequest(merchantDto);
        merchantValidator.validateDuplication(merchantDto, userService.getByUsernameOrAccountName(merchantDto
                .getEmail(), merchantDto.getAccountName()));
        merchantService.save(merchantBuilder.buildMerchant(merchantDto, roleService.getByName(RoleEnum.MERCHANT.getRole())));
    }

    @Transactional
    public void update(MerchantDto merchantDto, Long merchantId) {
        User user = getAndValidateUserByMerchantId(merchantId);
        merchantValidator.validateContactDetails(merchantDto.getEmail(), merchantDto.getPhone());
        merchantValidator.validateDuplication(merchantDto, userService.getByUsernameOrAccountNameAndUserId(merchantDto
                .getEmail(), merchantDto.getAccountName(), user.getUser_id()));
        merchantService.save(merchantBuilder.buildMerchant(merchantDto, user));
    }

    public MerchantDto getByAccountNo(String accountNo) {
        merchantValidator.validateAccountNo(accountNo);
        User merchant = merchantService.getByAccountNo(accountNo);
        if (Objects.isNull(merchant)) {
            throw new StorecastApiException(NOT_FOUND, merchantNotFoundByAccountNoErrorMessage, ACCOUNT_NO);
        }
        return merchantDtoConverter.convertMerchant(merchant);
    }

    public List<MerchantDto> getAll() {
        List<User> merchants = merchantService.getAll();
        if (CollectionUtils.isEmpty(merchants)) {
            throw new StorecastApiException(NOT_FOUND);
        }
        return merchantDtoConverter.convertMerchants(merchants);
    }

    private User getAndValidateUserByMerchantId(Long merchantId) {
        User user = userService.getByUserId(merchantId);
        if (Objects.isNull(user)) {
            throw new StorecastApiException(ErrorKey.BAD_REQUEST, merchantNotFoundErrorMessage, MERCHANT_ID);
        }
        return user;
    }
}
