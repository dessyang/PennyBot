package com.yjymh.penny.service.impl;

import cn.hutool.core.date.DateUtil;
import com.yjymh.penny.entity.BiliLive;
import com.yjymh.penny.mapper.BiliLiveMapper;
import com.yjymh.penny.requests.service.BiliRequestService;
import com.yjymh.penny.service.BiliLiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;

/**
 * @author yjymh
 */
@Service
public class BiliLiveServiceImpl implements BiliLiveService {

    @Autowired
    BiliLiveMapper biliLiveMapper;
    @Autowired
    BiliRequestService biliRequestService;

    public int addBiliLive(long uid, Long starFriend, Long starGroup) {
        BiliLive biliLive = new BiliLive();
        Date time = DateUtil.date();
        biliLive.setUid(uid);
        biliLive.setLiveId(biliRequestService.getRoomIdByUid(uid));
        biliLive.setName(biliRequestService.getName(uid));
        biliLive.setLiveStatus(biliRequestService.getLiveStateByUid(uid));
        if (starFriend != null) {
            biliLive.setStarFriend(String.valueOf(starFriend));
        }
        if (starGroup != null) {
            biliLive.setStarGroup(String.valueOf(starGroup));
        }
        biliLive.setCreateTime(time);
        biliLive.setUpdateTime(time);

        return biliLiveMapper.addBiliLive(biliLive);
    }

    @Override
    public int addBiliLiveByFriend(long uid, long starFriend) {
        return this.addBiliLive(uid, starFriend, null);
    }

    @Override
    public int addBiliLiveByGroup(long uid, long starGroup) {
        return this.addBiliLive(uid, null, starGroup);
    }

    @Override
    public boolean liveExits(Long uid) {
        boolean flag = false;
        BiliLive biliLive = biliLiveMapper.queryBiliLiveByUid(uid);
        if (biliLive != null) {
            flag = true;
        }
        return flag;
    }

    @Override
    public ArrayList<BiliLive> queryBiliLiveList() {
        return biliLiveMapper.queryBiliLiveList();
    }

    @Override
    public BiliLive queryBiliLiveByUid(Long uid) {
        return biliLiveMapper.queryBiliLiveByUid(uid);
    }

    @Override
    public int updateLiveState(long uid, boolean liveState) {
        BiliLive biliLive = new BiliLive();
        biliLive.setUid(uid);
        biliLive.setLiveStatus(liveState);
        biliLive.setUpdateTime(DateUtil.date());
        return biliLiveMapper.updateBiliLive(biliLive);
    }

    @Override
    public int updateBiliLive(BiliLive biliLive) {
        return biliLiveMapper.updateBiliLive(biliLive);
    }
}
