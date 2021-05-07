package com.yjymh.robot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yjymh.robot.entity.Dynamic;
import com.yjymh.robot.requests.BiliRequest;
import com.yjymh.robot.service.BiliRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiliRequestServiceImpl implements BiliRequestService {

    private static final Logger logger = LoggerFactory.getLogger(BiliRequestServiceImpl.class);

    private BiliRequest biliRequest;

    public BiliRequestServiceImpl() {
        biliRequest = new BiliRequest();
    }

    @Override
    public Long getRoomIdByUid(Long uid) {
        JSONObject info = biliRequest.getInfo(uid);
        return info.getJSONObject("data").getJSONObject("live_room").getLong("roomid");
    }

    @Override
    public int getLiveStateByRoomId(Long roomId) {
        int liveState = 0;
        JSONObject liveInfo = biliRequest.getLiveInfoByRoomId(roomId);
        if (liveInfo.getInteger("code") == 0) {
            liveState = liveInfo.getJSONObject("data").getInteger("live_status");
        }
        return liveState;
    }

    @Override
    public int getLiveStateByUid(Long uid) {
        long roomId = this.getRoomIdByUid(uid);
        return getLiveStateByRoomId(roomId);
    }

    @Override
    public Long getFans(Long uid) {
        JSONObject followInfo = biliRequest.getFollowInfo(uid);
        return followInfo.getJSONObject("data").getLong("follower");
    }

    @Override
    public Long getStar(Long uid) {
        JSONObject followInfo = biliRequest.getFollowInfo(uid);
        return followInfo.getJSONObject("data").getLong("following");
    }

    @Override
    public String getName(Long uid) {
        JSONObject info = biliRequest.getInfo(uid);
        return info.getJSONObject("data").getString("name");
    }

    @Override
    public int getSex(Long uid) {
        int sex = 2;
        JSONObject info = biliRequest.getInfo(uid);
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
        JSONObject info = biliRequest.getInfo(uid);
        return info.getJSONObject("data").getString("sign");
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
