package com.yjymh.penny.requests.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yjymh.penny.entity.BiliCookies;
import com.yjymh.penny.entity.Login;
import com.yjymh.penny.requests.BiliRequest;
import com.yjymh.penny.requests.service.BiliRequestService;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Connection.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author yjymh
 */
@Service
public class BiliRequestServiceImpl implements BiliRequestService {

    private static final Logger logger = LoggerFactory.getLogger(BiliRequestServiceImpl.class);

    @Getter
    @Setter
    private static Map<Long, BiliCookies> cookies;

    @Override
    public Long getRoomIdByUid(Long uid) {
        JSONObject info = BiliRequest.getInfo(uid);
        return info.getJSONObject("data").getJSONObject("live_room").getLong("roomid");
    }


    @Override
    public boolean getLiveStateByUid(Long uid) {
        JSONObject info = BiliRequest.getInfo(uid);
        Integer liveState = info.getJSONObject("data").getJSONObject("live_room").getInteger("liveStatus");
        return (liveState == 1) ? true : false;
    }

    @Override
    public Long getFans(Long uid) {
        JSONObject followInfo = BiliRequest.getFollowInfo(uid);
        return followInfo.getJSONObject("data").getLong("follower");
    }

    @Override
    public Long getStar(Long uid) {
        JSONObject followInfo = BiliRequest.getFollowInfo(uid);
        return followInfo.getJSONObject("data").getLong("following");
    }

    @Override
    public String getName(Long uid) {
        JSONObject info = BiliRequest.getInfo(uid);
        return info.getJSONObject("data").getString("name");
    }

    @Override
    public int getSex(Long uid) {
        int sex = 2;
        JSONObject info = BiliRequest.getInfo(uid);
        String s = info.getJSONObject("data").getString("sex");
        switch (s) {
            case "男":
                sex = 1;
                break;
            case "女":
                sex = 0;
                break;
            default:
        }
        return sex;
    }

    @Override
    public String getSign(Long uid) {
        JSONObject info = BiliRequest.getInfo(uid);
        return info.getJSONObject("data").getString("sign");
    }

    @Override
    public String getLiveTitle(long uid) {
        JSONObject liveInfoByUid = BiliRequest.getInfo(uid);
        return liveInfoByUid.getJSONObject("data").getJSONObject("live_room").getString("title");
    }


    @Override
    public String getLiveCoverLink(long uid) {
        JSONObject liveInfoByUid = BiliRequest.getInfo(uid);
        return liveInfoByUid.getJSONObject("data").getJSONObject("live_room").getString("cover");
    }

    /**
     * todo
     * <p>
     * https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/space_history?csrf=1578b6122d908819b39ab3ffaa77ccce&visitor_uid=108523059&host_uid=108523059&offset_dynamic_id=0&need_top=1&platform=web
     * host_uid = 108523059
     *
     * @param uid 用户id
     * @return
     */
    @Override
    public List<Object> getDynamicList(long uid) {
        return null;
    }

    @Override
    public Login getLoginInfo() {
        JSONObject loginUrl = BiliRequest.getLoginUrl();
        Login login = new Login();

        login.setOauthKey(loginUrl.getJSONObject("data").getString("oauthKey"));
        // 二维码登录创建的时间，180s之后oauthKey过时
        login.setStartTime(loginUrl.getLong("ts"));
        login.setQrLoginUrl(loginUrl.getJSONObject("data").getString("url"));

        return login;
    }

    @Override
    public Map<String, String> getLoginCookies(String oauthKey) {

        Map<String, String> cookies = null;

        Response loginInfo = BiliRequest.getLoginInfo(oauthKey);
        // 登录请求响应的json数据
        String body = loginInfo.body();
        JSONObject jsonObject = JSON.parseObject(body);
        // 登录状态
        Boolean status = jsonObject.getBoolean("status");
        if (status) {
            // 登录成功设置cookies
            cookies = loginInfo.cookies();
        } else {
            Integer data = jsonObject.getInteger("data");
            switch (data) {
                case -4:
                    logger.info("未扫描");
                    break;
                case -5:
                    logger.info("扫描成功，手机端待确认");
                default:
            }
        }
        return cookies;
    }


}
