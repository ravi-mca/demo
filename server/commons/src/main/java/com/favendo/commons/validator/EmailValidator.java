package com.favendo.commons.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.favendo.commons.constant.ValidationPatternConstant.EMAIL_PATTERN;

@Component
public class EmailValidator {
    public Boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }
}
