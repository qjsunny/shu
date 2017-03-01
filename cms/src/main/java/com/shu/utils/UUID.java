package com.shu.utils;

/**
 * Created by admin on 2017/1/8.
 */
public class UUID {
    public static String getID() {
        String str = java.util.UUID.randomUUID().toString();
        str = str.replaceAll("-", "");
        return str;
    }
}
