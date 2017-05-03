package com.favendo.commons.validator;

import com.favendo.commons.exception.ErrorKey;
import com.favendo.commons.exception.StorecastApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Component
public class EmptyOrNullValidator {

    public void validateIfNull(String string, ErrorKey errorKey, String errorDescription, String errorField) {
        if (StringUtils.isBlank(string)) {
            throw new StorecastApiException(errorKey, errorDescription, errorField);
        }
    }

    public void validateIfNull(Collection<?> collection, ErrorKey errorKey, String errorDescription, String errorField) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new StorecastApiException(errorKey, errorDescription, errorField);
        }
    }

    public void validateIfNull(Collection<?> collection, ErrorKey errorKey, String errorDescription) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new StorecastApiException(errorKey, errorDescription);
        }
    }

    public void validateIfNull(Object object, ErrorKey errorKey, String errorDescription, String errorField) {
        if (Objects.isNull(object)) {
            throw new StorecastApiException(errorKey, errorDescription, errorField);
        }
    }

    public void validateIfNull(Object object, ErrorKey errorKey, String errorDescription) {
        if (Objects.isNull(object)) {
            throw new StorecastApiException(errorKey, errorDescription);
        }
    }

    public void validateIfNull(Object object, ErrorKey errorKey) {
        if (Objects.isNull(object)) {
            throw new StorecastApiException(errorKey);
        }
    }

    public void validateIfNull(ErrorKey errorKey, String errorDescription, Object... objects) {
        for (Object object : objects) {
            validateIfNull(object, errorKey, errorDescription);
        }
    }

    public void validateFieldIfNull(String string, ErrorKey errorKey, String messageKey, String errorField) {
        if (StringUtils.isBlank(string)) {
            throw new StorecastApiException(errorKey, messageKey, errorField);
        }
    }

    public void validateFieldIfNull(Collection<?> collection, ErrorKey errorKey, String messageKey, String errorField) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new StorecastApiException(errorKey, messageKey, errorField);
        }
    }

    public void validateFieldIfNull(Collection<?> collection, ErrorKey errorKey, String messageKey) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new StorecastApiException(errorKey, messageKey);
        }
    }

    public void validateFieldIfNull(Object object, ErrorKey errorKey, String messageKey, String errorField) {
        if (Objects.isNull(object)) {
            throw new StorecastApiException(errorKey, messageKey, errorField);
        }
    }

    public void validateFieldIfNull(Object object, ErrorKey errorKey, String messageKey) {
        if (Objects.isNull(object)) {
            throw new StorecastApiException(errorKey, messageKey);
        }
    }

    public void validateIfAllNull(ErrorKey errorKey, String errorDescription, Integer... objects) {
        if (!Arrays.stream(objects).filter(Objects::nonNull).findAny().isPresent()) {
            throw new StorecastApiException(errorKey, errorDescription);
        }
    }

    public void validateFieldIfNull(Object object, int status, String errorType, String message, String errorField) {
        if (Objects.isNull(object)) {
            throw new StorecastApiException(status, errorType, message, errorField);
        }
    }

    public void validateIfAllNull(ErrorKey errorKey, String errorDescription, Object... objects) {
        if (!Arrays.stream(objects).filter(Objects::nonNull).findAny().isPresent()) {
            throw new StorecastApiException(errorKey, errorDescription);
        }
    }

    public void validateFieldIfNotNull(String string, ErrorKey errorKey, String messageKey, String errorField) {
        if (StringUtils.isNotEmpty(string)) {
            throw new StorecastApiException(errorKey, messageKey, errorField);
        }
    }

    public void validateIfNotNull(Object object, ErrorKey errorKey,String errorMessage) {
        if (Objects.nonNull(object)) {
            throw new StorecastApiException(errorKey,errorMessage);
        }
    }

    public void validateIfNotNull(Collection<?> collection, ErrorKey errorKey,String errorMessage) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new StorecastApiException(errorKey,errorMessage);
        }
    }
}
