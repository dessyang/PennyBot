package com.yjymh.robot.service;

import com.alibaba.fastjson.JSONObject;
import com.yjymh.robot.requests.BiliRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaConstructorAutowiringInspection")
@Service
public class BiliService {

    private static final Logger logger = LoggerFactory.getLogger(BiliService.class);

    private BiliRequest biliRequest;

    public BiliService () {
        biliRequest = new BiliRequest();
    }


    /**
     * 获取直播间状态
     */
    public boolean getLiveStateByRoomId(long roomId) {
        boolean liveState = false;
        JSONObject liveInfo = biliRequest.getLiveInfoByRoomId(roomId);
        if (liveInfo.getInteger("code") == 0) {
            if (liveInfo.getJSONObject("data").getInteger("live_status") == 1) {
                liveState = true;
            }
        }
        return liveState;
    }

    /**
     * 获取直播间状态
     */
    public boolean getLiveStateByUid(long uid) {
        long roomId = biliRequest.getRoomIdByUid(uid);
        return getLiveStateByRoomId(roomId);
    }

    /**
     * 获取粉丝数
     */
    public Long getFans(long uid) {
        JSONObject followInfo = biliRequest.getFollowInfo(uid);
        return followInfo.getJSONObject("data").getLong("follower");
    }

    /**
     * 获取关注数
     * @return
     */
    public Long getStar(long uid) {
        JSONObject followInfo = biliRequest.getFollowInfo(uid);
        return followInfo.getJSONObject("data").getLong("following");
    }

    /**
     * 获取用户昵称
     * @return
     */
    public String getName(long uid) {
        JSONObject info = biliRequest.getInfo(uid);
        return info.getJSONObject("data").getString("name");
    }

    /**
     * 获取性别
     * @return
     */
    public int getSex(long uid) {
        int sex = 2;
        JSONObject info = biliRequest.getInfo(uid);
        String s = info.getJSONObject("data").getString("sex");
        switch (s){
            case "男":
                sex = 1;
                break;
            case "女":
                sex = 0;
                break;
        }
        return sex;
    }

    public static void main(String[] args) {

    }
}
