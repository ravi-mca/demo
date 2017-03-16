package com.favendo.user.service.utils;

import com.favendo.user.service.domain.StorecastUser;
import com.favendo.user.service.domain.StorecastUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class StorecastUserContextHolder {

    public static StorecastUser getLoggedInUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal == null) {
                return null;
            } else {
                return ((StorecastUserDetails) principal).getStorecastUser();
            }
        } else {
            return null;
        }
    }

    public static Long getLoggedInUserId() {
        if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (Objects.isNull(principal)) {
                return null;
            } else {
                if (principal instanceof StorecastUserDetails) {
                    return ((StorecastUserDetails) principal).getStorecastUser().getUser_id();
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public static String getLoggedInUsername() {
        if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (Objects.isNull(principal)) {
                return null;
            } else {
                if (principal instanceof StorecastUserDetails) {
                    return ((StorecastUserDetails) principal).getStorecastUser().getUsername();
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }
}
