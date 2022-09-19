package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	// We are using here static so that we do not need to create the object of the
	// Class

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final int SMALL_DEFAULT_TIME_OUT = 5;
	public static final int MEDIUM_DEFAULT_TIME_OUT = 10;
	public static final int LARGE_DEFAULT_TIME_OUT = 20;
	public static final String ACCOUNTS_PAGE_URL_FRACTION = "route=account/account";
	public static final List<String> EXPECTED_ACCOUNTS_HEADERS_LIST = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter"); // ArrayList Literals :- this is how ArrayList litterals define
	public static final String REGISTER_SUCCESS_MESG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "Register";
	public static final String PRODUCT_SHEET_NAME = "Product";
	

}
