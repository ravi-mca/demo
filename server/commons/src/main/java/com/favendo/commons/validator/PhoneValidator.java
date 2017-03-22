package com.favendo.commons.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.favendo.commons.constant.ValidationPatternConstant.EMAIL_PATTERN;
import static com.favendo.commons.constant.ValidationPatternConstant.PHONE_PATTERN;

@Component
public class PhoneValidator {
    public Boolean validatePhone(String phone) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        return pattern.matcher(phone).matches();
    }
}
