package com.yjymh.penny.utils;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

/**
 * @author yjymh
 */
public class Md5Util {
    public static String toMd5(String s) {
        String info = System.currentTimeMillis() + s;
        return md5DigestAsHex(info.getBytes());
    }
}
