package com.favendo.commons.validator;

import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.exception.StorecastApiException;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

@Component
public class BooleanValidator {

    public void validateIfTrue(Boolean value, ErrorKey errorKey, String message) {
        if (BooleanUtils.isTrue(value)) {
            throw new StorecastApiException(errorKey, message);
        }
    }

    public void validateIfFalse(Boolean value, ErrorKey errorKey, String message) {
        if (BooleanUtils.isFalse(value)) {
            throw new StorecastApiException(errorKey, message);
        }
    }

    public void validateIfFalse(Boolean value, ErrorKey errorKey, String message, String field) {
        if (BooleanUtils.isFalse(value)) {
            throw new StorecastApiException(errorKey, message, field);
        }
    }
}
