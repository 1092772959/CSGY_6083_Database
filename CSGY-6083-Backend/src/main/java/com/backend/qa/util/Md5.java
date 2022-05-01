package com.backend.qa.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5 {
    public static String md5(String key){
        return DigestUtils.md5Hex(key);
    }

    public static final String salt = "1a2b3c4d";

    public static String inputPass2DBPass ( String  inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2)+ inputPass + salt.charAt(5) + salt.charAt(4) ;
        return md5(str);
    }

}