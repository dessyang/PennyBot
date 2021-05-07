package com.yjymh.robot.service;

import com.yjymh.robot.entity.Dynamic;

import java.util.List;

public interface BiliRequestService {
    /**
     * 通过用户id获取房间号
     *
     * @param uid 用户id
     * @return 直播间
     */
    Long getRoomIdByUid(Long uid);

    /**
     * 获取直播间状态
     *
     * @param roomId 房间号
     * @return 直播状态 0.未开播 1.直播 2.轮播
     */
    int getLiveStateByRoomId(Long roomId);

    /**
     * 获取直播间状态
     *
     * @param uid 用户uid
     * @return 直播状态 0.未开播 1.直播 2.轮播
     */
    int getLiveStateByUid(Long uid);

    /**
     * 获取粉丝数
     *
     * @param uid 用户uid
     * @return 粉丝数
     */
    Long getFans(Long uid);

    /**
     * 获取关注数
     *
     * @param uid 用户uid
     * @return 关注数
     */
    Long getStar(Long uid);

    /**
     * 获取用户昵称
     *
     * @param uid 用户uid
     * @return 用户昵称
     */
    String getName(Long uid);

    /**
     * 获取性别
     *
     * @param uid 用户uid
     * @return 性别 0：女 1：男 2；未知
     */
    int getSex(Long uid);

    /**
     * 获取用户签名
     *
     * @param uid 用户uid
     * @return 用户签名
     */
    String getSign(Long uid);

    /**
     * 获取最新的一个动态
     *
     * @param uid 用户uid
     * @return 动态封装类
     */
    Dynamic getNewDynamic(Long uid);

    /**
     * 获取所有的动态
     *
     * @param uid 用户uid
     * @return List<Dynamic>
     */
    List<Dynamic> getDynamicList(Long uid);
}
