package com.favendo.commons.validator;

import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.exception.StorecastApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EqualsValidator {

    public void validateIfEqualsIgnoreCase(String value1, String value2, ErrorKey errorKey, String message) {
        if (StringUtils.endsWithIgnoreCase(value1, value2)) {
            throw new StorecastApiException(errorKey, message);
        }
    }
}
