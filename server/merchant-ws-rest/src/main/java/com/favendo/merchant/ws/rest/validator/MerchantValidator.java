package com.favendo.merchant.ws.rest.validator;

import com.favendo.commons.domain.User;
import com.favendo.commons.validator.*;
import com.favendo.merchant.ws.rest.dto.MerchantDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.favendo.commons.exception.ErrorKey.ALREADY_EXISTS;
import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;
import static com.favendo.user.service.constant.UserConstant.*;

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

    @Value("${duplicate.merchant.firstName.error.message}")
    private String duplicateMerchantFirstNameErrorMessage;

    @Value("${invalid.merchant.accountNo.error.message}")
    private String invalidMerchantAccountNumberErrorMessage;

    public void validateRequest(MerchantDto merchantDto) {
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
        validateContactDetails(merchantDto.getEmail(), merchantDto.getPhone());
    }

    public void validateContactDetails(String email, String phone) {
        if (StringUtils.isNotEmpty(email)) {
            booleanValidator.validateIfFalse(emailValidator.validateEmail(email), BAD_REQUEST,
                    invalidMerchantEmailErrorMessage, EMAIL);
        }
        if (StringUtils.isNotEmpty(phone)) {
            booleanValidator.validateIfFalse(phoneValidator.validatePhone(phone), BAD_REQUEST,
                    invalidMerchantPhoneErrorMessage, PHONE);
        }
    }

    public void validateDuplication(MerchantDto merchantDto, User user) {
        if (Objects.nonNull(user)) {
            equalsValidator.validateIfEqualsIgnoreCase(merchantDto.getFirstName(), user.getFirstName(), ALREADY_EXISTS,
                    duplicateMerchantFirstNameErrorMessage);
            equalsValidator.validateIfEqualsIgnoreCase(merchantDto.getEmail(), user.getUsername(), ALREADY_EXISTS,
                    duplicateMerchantEmailErrorMessage);
            equalsValidator.validateIfEqualsIgnoreCase(merchantDto.getAccountName(), user.getAccountName(), ALREADY_EXISTS,
                    duplicateMerchantAccountNameErrorMessage);
        }
    }

    public void validateAccountNo(String accountNo) {
        emptyOrNullValidator.validateIfNull(accountNo, BAD_REQUEST, invalidMerchantAccountNumberErrorMessage, ACCOUNT_NO);
    }
}
