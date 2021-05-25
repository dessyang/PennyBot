package com.yjymh.penny.requests.service;

import com.yjymh.penny.entity.Login;

import java.util.List;
import java.util.Map;

/**
 * @author yjymh
 */
public interface BiliRequestService {
    /**
     * 通过用户id获取房间号
     *
     * @param uid 用户id
     * @return 直播间id
     */
    Long getRoomIdByUid(Long uid);

    /**
     * 获取直播间状态
     *
     * @param uid 用户uid
     * @return 直播状态 0.未开播 1.直播 2.轮播
     */
    boolean getLiveStateByUid(Long uid);

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
     * 获取直播间标题
     *
     * @param uid 用户uid
     * @return 直播间标题
     */
    String getLiveTitle(long uid);

    /**
     * 获取直播间封面链接
     *
     * @param uid 用户uid
     * @return 直播封面链接
     */
    String getLiveCoverLink(long uid);

    /**
     * 获取最新动态
     *
     * @param uid 用户id
     * @return 动态数据
     */
    List<Object> getDynamicList(long uid);

    /**
     * 获取登录相关的数据
     *
     * @return Login类
     */
    Login getLoginInfo();

    /**
     * 使用密钥获取cookies
     *
     * @param oauthKey
     * @return
     */
    Map<String, String> getLoginCookies(String oauthKey);

}
