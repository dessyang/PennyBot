package com.yjymh.robot.requests;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BiliRequest extends BaseRequest {

    private static final Logger logger = LoggerFactory.getLogger(BiliRequest.class);

    private static final String FOLLOW_URL = "x/relation/stat?vmid=%s";
    private static final String INFO_BASE_URL = "https://api.bilibili.com/";
    private static final String LIVE_BASE_URL = "https://api.live.bilibili.com/";
    private static final String infoUrl = "x/space/acc/info?mid=%s";
    private static final String statusUrl = "room/v1/Room/room_init?id=%s";
    private static final String QRCODE_LOGIN_URL = "http://passport.bilibili.com/qrcode/getLoginUrl";

    /**
     * 获取直播间相关信息
     *
     * @param roomId 房间号
     * @return jsonObject
     */
    public JSONObject getLiveInfoByRoomId(long roomId) {
        String url = LIVE_BASE_URL + String.format(statusUrl, roomId);
        return getDataByAPI(url);
    }

    /**
     * 获取关注信息
     *
     * @param uid 用户id
     * @return jsonObject
     */
    public JSONObject getFollowInfo(long uid) {
        String url = INFO_BASE_URL + String.format(FOLLOW_URL, uid);
        return getDataByAPI(url);
    }

    /**
     * 获取用户主要信息
     *
     * @param uid 用户id
     * @return jsonObject
     */
    public JSONObject getInfo(long uid) {
        String url = INFO_BASE_URL + String.format(infoUrl, uid);
        return getDataByAPI(url);
    }

    /**
     * 二维码登录
     *
     * @return 返回登录链接
     */
    public JSONObject getCookieByQRCode() {
        return getDataByAPI(QRCODE_LOGIN_URL);
    }
}
