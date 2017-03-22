package com.favendo.commons.enums;


public enum RoleEnum {

    ADMIN("ROLE_ADMIN"),
    MERCHANT("ROLE_MERCHANT");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
