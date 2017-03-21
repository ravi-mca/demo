package com.favendo.merchant.service.validator;

import com.favendo.commons.validator.*;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.user.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.favendo.commons.exception.ErrorKey.ALREADY_EXISTS;
import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.user.service.constant.StorecastUserConstant.*;

@Component
public class MerchantValidator {

    @Autowired
    private PhoneValidator phoneValidator;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private EqualsValidator equalsValidator;

    @Autowired
    private BooleanValidator booleanValidator;

    @Autowired
    private EmptyOrNullValidator emptyOrNullValidator;

    @Value("${invalid.merchant.request.error.message}")
    private String invalidMerchantRequestErrorMessage;

    @Value("${empty.merchant.firstname.error.message}")
    private String emptyMerchantFirstNameErrorMessage;

    @Value("${empty.merchant.lastname.error.message}")
    private String emptyMerchantLastNameErrorMessage;

    @Value("${empty.merchant.email.error.message}")
    private String emptyMerchantEmailErrorMessage;

    @Value("${empty.merchant.phone.error.message}")
    private String emptyMerchantPhoneErrorMessage;

    @Value("${empty.merchant.accountname.error.message}")
    private String emptyMerchantAccountNameErrorMessage;

    @Value("${invalid.merchant.email.error.message}")
    private String invalidMerchantEmailErrorMessage;

    @Value("${invalid.merchant.phone.error.message}")
    private String invalidMerchantPhoneErrorMessage;

    @Value("${duplicate.merchant.email.error.message}")
    private String duplicateMerchantEmailErrorMessage;

    @Value("${duplicate.merchant.accountName.error.message}")
    private String duplicateMerchantAccountNameErrorMessage;

    public void validateMerchantRequest(MerchantDto merchantDto) {
        emptyOrNullValidator.validateIfNull(merchantDto, BAD_REQUEST, invalidMerchantRequestErrorMessage);
        emptyOrNullValidator.validateFieldIfNull(merchantDto.getFirstName(), BAD_REQUEST,
                emptyMerchantFirstNameErrorMessage, FIRSTNAME);
        emptyOrNullValidator.validateFieldIfNull(merchantDto.getLastName(), BAD_REQUEST,
                emptyMerchantLastNameErrorMessage, LASTNAME);
        emptyOrNullValidator.validateFieldIfNull(merchantDto.getPhone(), BAD_REQUEST,
                emptyMerchantPhoneErrorMessage, PHONE);
        emptyOrNullValidator.validateFieldIfNull(merchantDto.getEmail(), BAD_REQUEST,
                emptyMerchantEmailErrorMessage, EMAIL);
        emptyOrNullValidator.validateFieldIfNull(merchantDto.getAccountName(), BAD_REQUEST,
                emptyMerchantAccountNameErrorMessage, ACCOUNTNAME);
        booleanValidator.validateIfFalse(emailValidator.validateEmail(merchantDto.getEmail()), BAD_REQUEST,
                invalidMerchantEmailErrorMessage, EMAIL);
        booleanValidator.validateIfFalse(phoneValidator.validatePhone(merchantDto.getPhone()), BAD_REQUEST,
                invalidMerchantPhoneErrorMessage, PHONE);
    }

    public void validateDuplicateMerchant(MerchantDto merchantDto, User storecastUser) {
        if (Objects.nonNull(storecastUser)) {
            equalsValidator.validateIfEquals(merchantDto.getEmail(), storecastUser.getUsername(), ALREADY_EXISTS,
                    duplicateMerchantEmailErrorMessage);
            equalsValidator.validateIfEquals(merchantDto.getAccountName(), storecastUser.getAccountName(), ALREADY_EXISTS,
                    duplicateMerchantAccountNameErrorMessage);
        }
    }
}
