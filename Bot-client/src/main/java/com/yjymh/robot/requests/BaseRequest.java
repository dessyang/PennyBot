package com.yjymh.robot.requests;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * 网络请求基类，用来存放通用代码
 */
public class BaseRequest {

    protected final String DEFAULT_UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36";
    // 存放请求返回的原始报文
    protected String body;

    /**
     * 获取api数据映射到实体类
     *
     * @param url      网页链接
     * @param classOfT 不知道
     * @param <T>      不知道
     * @return 封装类
     */
    protected <T> T getDataByAPI(String url, Class<T> classOfT) {
        try {
            body = Jsoup.connect(url)
                    .userAgent(DEFAULT_UA)
                    .ignoreContentType(true)
                    .execute()
                    .body();
            return JSON.parseObject(body, classOfT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取api数据返回json对象
     *
     * @param url 链接
     * @return JSONObject (fastjson)
     */
    protected JSONObject getDataByAPI(String url) {
        return getDataByAPI(url, JSONObject.class);
    }
}
