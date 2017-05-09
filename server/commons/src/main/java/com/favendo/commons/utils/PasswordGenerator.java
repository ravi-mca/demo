package com.favendo.commons.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PasswordGenerator {

    public static final String PWD_GENERATOR_STR = "AB12ab@#BCbc34!$DEde56%&FGfg78@#HIhi78$%JKjk89%$#@";

    public  String getStrongRandomPassword() {
        String passwordGeneratorStr = PWD_GENERATOR_STR;
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(passwordGeneratorStr.charAt(secureRandom.nextInt(passwordGeneratorStr.length())));
        }
        return sb.toString();
    }
}
