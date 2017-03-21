package com.favendo.commons.validator;

import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.exception.StorecastApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EqualsValidator {

    public void validateIfEquals(String value1, String value2, ErrorKey errorKey, String message) {
        if (StringUtils.equals(value1, value2)) {
            throw new StorecastApiException(errorKey, message);
        }
    }

    public void validateIfNotEquals(String value1, String value2, ErrorKey errorKey, String message) {
        if (!StringUtils.equals(value1, value2)) {
            throw new StorecastApiException(errorKey, message);
        }
    }

    public void validateIfNotEquals(Integer integer1, Integer integer2, ErrorKey errorKey, String message) {
        if (!Objects.equals(integer1, integer2)) {
            throw new StorecastApiException(errorKey, message);
        }
    }

    public void validateIfEquals(Integer integer1, Integer integer2, ErrorKey errorKey, String message) {
        if (Objects.equals(integer1, integer2)) {
            throw new StorecastApiException(errorKey, message);
        }
    }
}
