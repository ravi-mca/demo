package com.favendo.commons.validator;

import org.springframework.stereotype.Component;

import static com.favendo.commons.constant.ValidationPatternConstant.ZIPCODE_PATTERN;

@Component
public class ZipCodeValidator {

    public Boolean validateZipCode(final String pin) {
        return pin.matches(ZIPCODE_PATTERN);
    }
}
