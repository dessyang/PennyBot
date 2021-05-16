package com.yjymh.penny.utils;

import com.alibaba.fastjson.JSON;
import com.yjymh.penny.entity.Response;

import java.util.HashMap;

/**
 * @author yjymh
 */
public class ResponseUtil {
    /**
     * 设置返回数据
     */
    public static String setResponse(Response resp) {
        return JSON.toJSONString(new HashMap<Integer, String>() {{
            put(resp.getCode(), resp.getDesc());
        }});
    }
}