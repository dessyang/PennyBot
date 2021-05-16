package com.yjymh.penny.utils;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

/**
 * @author yjymh
 */
public class MD5Util {
    public static String toMD5(String s) {
        String info = System.currentTimeMillis() + s;
        return md5DigestAsHex(info.getBytes());
    }
}
