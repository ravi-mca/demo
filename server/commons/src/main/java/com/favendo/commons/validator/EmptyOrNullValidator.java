package com.favendo.commons.validator;

import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.exception.StorecastApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;

@Component
public class EmptyOrNullValidator {

    public void validateIfNull(String string, ErrorKey errorKey, String errorDescription, String errorField) {
        if (StringUtils.isBlank(string)) {
            throw new StorecastApiException(errorKey, errorDescription, errorField);
        }
    }

    public void validateIfNull(Object object, ErrorKey errorKey, String errorDescription) {
        if (Objects.isNull(object)) {
            throw new StorecastApiException(errorKey, errorDescription);
        }
    }

    public void validateFieldIfNull(String string, ErrorKey errorKey, String messageKey, String errorField) {
        if (StringUtils.isBlank(string)) {
            throw new StorecastApiException(errorKey, messageKey, errorField);
        }
    }

    public void validateFieldIfNull(Object object, ErrorKey errorKey, String messageKey) {
        if (Objects.isNull(object)) {
            throw new StorecastApiException(errorKey, messageKey);
        }
    }

    public void validateIfNotNull(Object object, ErrorKey errorKey, String errorMessage) {
        if (Objects.nonNull(object)) {
            throw new StorecastApiException(errorKey, errorMessage);
        }
    }

    public void validateIfNotNull(Collection<?> collection, ErrorKey errorKey, String errorMessage) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new StorecastApiException(errorKey, errorMessage);
        }
    }
}
