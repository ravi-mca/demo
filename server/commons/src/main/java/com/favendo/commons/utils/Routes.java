package com.favendo.commons.utils;

public class Routes {

	public static final String ALL_REQUEST = "/**";

	public static final String ADMIN_REQUEST = "/api/admin/*";

	public static final String CUSTOMER_REQUEST = "/api/customer/*";

	public static final String MERCHANT_REQUEST = "/api/merchant/*";

	public static final String ROOT = "/api";
	
	public static final String ADMIN = "/admin";

	public static final String LOGIN = "/login";

	public static final String LOGIN_REQUEST =  ROOT + LOGIN;

	public static final String MERCHANT = "/merchant";
	
	public static final String MERCHANT_ID = "merchantId";

	public static final String PATH_PARAM_MERCHANT_ID = "/{merchantId}";

	public static final String PATH_PARAM_CUSTOMER_ID = "/{customerId}";
	
	public static final String ACCOUNT_NO = "accountNo";
	
	public static final String PATH_PARAM_ACCOUNT_NO = "/{accountNo}";

	public static final String STORE = "/store";

	public static final String CUSTOMER = "/customer";

	public static final String ID = "id";

	public static final String STORE_ID = "storeId";

	public static final String PATH_PARAM_STORE_ID = "/{storeId}";

	public static final String PATH_PARAM_ID = "/{id}";
	
	public static final String LIST = "/list";

	public static final String CREDENTIALS = "/credentials";

	public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/api/login";

	public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/**";

}
