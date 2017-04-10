package com.favendo.commons.constant;

public final class ValidationPatternConstant {

    private ValidationPatternConstant() {}
    
    public static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/\\\\=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static final String PHONE_PATTERN = "[0-9 +]+";

    public static final String ZIPCODE_PATTERN = "\\d{4,6}(-\\d{4})?";
}
