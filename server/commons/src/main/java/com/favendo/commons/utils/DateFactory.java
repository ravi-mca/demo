package com.favendo.commons.utils;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateFactory {

    public Date getDateByMilliseconds() {
        return new Date(getCurrentTimeMilliseconds());
    }

    public Date getDateByMilliseconds(Long milliseconds) {
        return new Date(getTokenExpirationInMillis(milliseconds));
    }

    private Long getTokenExpirationInMillis(Long milliseconds) {
        return getCurrentTimeMilliseconds() + milliseconds;
    }

    private Long getCurrentTimeMilliseconds() {
        return System.currentTimeMillis();
    }
}
