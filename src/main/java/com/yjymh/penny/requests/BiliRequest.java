package com.yjymh.penny.requests;

import com.alibaba.fastjson.JSONObject;
import com.yjymh.penny.utils.HttpUtil;

/**
 * @author yjymh
 */
public class BiliRequest {
    private static final String INFO_BASE_URL = "https://api.bilibili.com/";
    private static final String FOLLOW_URL = "x/relation/stat?vmid=%s";
    private static final String INFO_URL = "x/space/acc/info?mid=%s";

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
}
