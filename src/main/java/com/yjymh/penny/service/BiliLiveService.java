package com.yjymh.penny.service;

import com.yjymh.penny.entity.BiliLive;

import java.util.ArrayList;

/**
 * @author yjymh
 */
public interface BiliLiveService {

    boolean liveExits(Long uid);

    ArrayList<BiliLive> queryLiveId();

    BiliLive queryBiliLiveByUid(Long uid);

    int updateLiveState(long uid);

    int addBiliLive(BiliLive biliLive);

    int updateBiliLive(BiliLive biliLive);
}
