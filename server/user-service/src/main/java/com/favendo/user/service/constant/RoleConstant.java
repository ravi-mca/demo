package com.favendo.user.service.constant;

public class RoleConstant {

    public static final String HAS_ADMIN_ROLE = "hasRole('ADMIN')";

    public static final String HAS_CUSTOMER_ROLE = "hasRole('CUSTOMER')";

    public static final String HAS_MERCHANT_ROLE = "hasRole('MERCHANT')";
    
    public static final String HAS_ANY_ROLE = "hasAnyRole('ADMIN' , 'CUSTOMER', 'MERCHANT')";
}
