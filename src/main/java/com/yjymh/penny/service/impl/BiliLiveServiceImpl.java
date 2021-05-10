package com.yjymh.penny.service.impl;

import com.yjymh.penny.entity.BiliLive;
import com.yjymh.penny.entity.KeyWord;
import com.yjymh.penny.mapper.BiliLiveMapper;
import com.yjymh.penny.service.BiliLiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BiliLiveServiceImpl implements BiliLiveService {

    @Autowired
    BiliLiveMapper biliLiveMapper;

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
    public ArrayList<BiliLive> queryLiveId() {
        return biliLiveMapper.queryLiveId();
    }

    @Override
    public BiliLive queryBiliLiveByUid(Long uid) {
        return biliLiveMapper.queryBiliLiveByUid(uid);
    }

    @Override
    public int updateLiveState(long uid) {
        BiliLive biliLive = new BiliLive(uid);
        return biliLiveMapper.updateBiliLive(biliLive);
    }

    @Override
    public int addBiliLive(BiliLive biliLive) {
        return biliLiveMapper.addBiliLive(biliLive);
    }

    @Override
    public int updateBiliLive(BiliLive biliLive) {
        return biliLiveMapper.updateBiliLive(biliLive);
    }
}
