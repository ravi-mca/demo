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

    public Long getCurrentTimeMilliseconds() {
        return System.currentTimeMillis();
    }

    private Long getTokenExpirationInMillis(Long milliseconds) {
        return getCurrentTimeMilliseconds() + milliseconds;
    }
}
