package com.favendo.commons.utils;

import java.util.UUID;

public class UniqueIdGenerator {

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }

}
