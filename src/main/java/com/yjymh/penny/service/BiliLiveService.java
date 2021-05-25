package com.yjymh.penny.service;

import com.yjymh.penny.entity.BiliLive;

import java.util.ArrayList;

/**
 * @author yjymh
 */
public interface BiliLiveService {

    /**
     * 添加新关注来自好友
     *
     * @param uid        up的uid
     * @param starFriend 好友列表
     * @return int
     * @since 1.1.0
     */
    int addBiliLiveByFriend(long uid, long starFriend);

    /**
     * 添加新关注来自群组
     *
     * @param uid       up的uid
     * @param starGroup 群组列表
     * @return int
     * @since 1.1.0
     */
    int addBiliLiveByGroup(long uid, long starGroup);

    /**
     * 查询该up是否有其他人关注
     *
     * @param uid up的uid
     * @return true：有其他人关注 false 没有其他人关注
     * @since 1.1.0
     */
    boolean liveExits(Long uid);

    /**
     * 查询up的关注信息
     *
     * @param uid up的uid
     * @return up主的关注信息
     * @since 1.1.0
     */
    BiliLive queryBiliLiveByUid(Long uid);

    /**
     * 查询所有up的关注信息
     *
     * @return 所有up的关注信息
     * @since 1.1.0
     */
    ArrayList<BiliLive> queryBiliLiveList();

    /**
     * 更新up的直播状态
     *
     * @param uid up的uid
     * @param liveState 直播状态
     * @return int
     * @since 1.1.0
     */
    int updateLiveState(long uid, boolean liveState);

    /**
     * 更新up的关注信息
     *
     * @param biliLive up的关注信息
     * @return int
     * @since 1.1.0
     */
    int updateBiliLive(BiliLive biliLive);
}
