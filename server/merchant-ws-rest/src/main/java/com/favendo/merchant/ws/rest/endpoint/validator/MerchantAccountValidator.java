package com.favendo.merchant.ws.rest.endpoint.validator;

import static com.favendo.commons.exception.ErrorKey.BAD_REQUEST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import static com.favendo.user.service.constant.StorecastUserConstant.*;

import com.favendo.commons.validator.EmptyOrNullValidator;

@Component
public class MerchantAccountValidator {

    @Autowired
    private EmptyOrNullValidator emptyOrNullValidator;
    
    @Value("${invalid.merchant.accountNo.error.message}")
    private String invalideMerchantAccountNumberErrorMessage;
    
    public void validateMerchantAccountNo(String accountNo) {
        emptyOrNullValidator.validateIfNull(accountNo, BAD_REQUEST, invalideMerchantAccountNumberErrorMessage, ACCOUNT_NO);
    }
}
