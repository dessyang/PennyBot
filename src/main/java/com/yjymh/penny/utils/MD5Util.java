package com.yjymh.penny.utils;

import java.util.Date;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

public class MD5Util {
    public static String toMD5(String s) {
        String info = new Date().getTime() + s;
        return md5DigestAsHex(info.getBytes());
    }
}
