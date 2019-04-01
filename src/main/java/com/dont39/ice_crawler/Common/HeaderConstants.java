package com.dont39.ice_crawler.Common;

import java.util.ResourceBundle;

public class HeaderConstants {
    private static ResourceBundle bundle = ResourceBundle.getBundle("setting");
    public static String USERAGENT = bundle.getString("header_userAgent");
    public static String COOKIE = bundle.getString("header_cookie");

}
