package com.favendo.commons.enums;


public enum RoleEnum {

    ADMIN("ROLE_ADMIN"),
    CUSTOMER("ROLE_CUSTOMER"),
    MERCHANT("ROLE_MERCHANT");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
