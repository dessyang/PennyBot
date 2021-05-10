package com.yjymh.penny.requests.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yjymh.penny.entity.Dynamic;
import com.yjymh.penny.requests.BiliRequest;
import com.yjymh.penny.requests.service.BiliRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BiliRequestServiceImpl implements BiliRequestService {

    private static final Logger logger = LoggerFactory.getLogger(BiliRequestServiceImpl.class);

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

    @Override
    public Dynamic getNewDynamic(Long uid) {
        // TODO
        return null;
    }

    @Override
    public List<Dynamic> getDynamicList(Long uid) {
        // TODO
        return null;
    }

}
