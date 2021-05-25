package com.yjymh.penny.requests;

import com.alibaba.fastjson.JSONObject;
import com.yjymh.penny.utils.HttpUtil;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author yjymh
 */
public class BiliRequest {
    private static final String BASE_URL = "http://www.bilibili.com";
    private static final String INFO_BASE_URL = "https://api.bilibili.com/";
    private static final String LOGIN_BASE_URL = "http://passport.bilibili.com/";
    private static final String FOLLOW_URL = "x/relation/stat?vmid=%s";
    private static final String INFO_URL = "x/space/acc/info?mid=%s";
    private static final String LOGIN_URL = "qrcode/getLoginUrl";
    private static final String LOGIN_INFO = "qrcode/getLoginInfo";

    /**
     * 获取用户相关信息
     *
     * @param uid 用户id
     * @return jsonObject
     */
    public static JSONObject getInfo(long uid) {
        String url = INFO_BASE_URL + String.format(INFO_URL, uid);
        return HttpUtil.getDataByApi(url);
    }

    /**
     * 获取关注信息
     *
     * @param uid 用户id
     * @return jsonObject
     */
    public static JSONObject getFollowInfo(long uid) {
        String url = INFO_BASE_URL + String.format(FOLLOW_URL, uid);
        return HttpUtil.getDataByApi(url);
    }

    /**
     * 获取登录
     */
    public static JSONObject getLoginUrl() {
        String url = LOGIN_BASE_URL + LOGIN_URL;
        return HttpUtil.getDataByApi(url);
    }

    /**
     * 获取登录信息
     * @param oauthKey 登录密钥
     * @return
     */
    public static Response getLoginInfo(String oauthKey) {
        String url = LOGIN_BASE_URL + LOGIN_INFO;
        Response execute = null;
        HashMap<String, String> data = new HashMap<String, String>() {{
            put("oauthKey", oauthKey);
            put("gourl", BASE_URL);
        }};

        try {
            execute = Jsoup.connect(url)
                    .header("content-type", " application/x-www-form-urlencoded")
                    .data(data)
                    .ignoreContentType(true)
                    .method(Method.POST)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execute;
    }

}
