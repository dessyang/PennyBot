package com.yjymh.robot.service;

import com.yjymh.robot.entity.Token;

public interface TokenService {

    /**
     * 获取token的数据
     *
     * @param type  token的类型
     * @param token token的值
     * @return token封装类
     */
    Token getTokenInfo(String type, String token);

    /**
     * 通过账号发送消息
     *
     * @param type    消息的类型
     * @param account 用户
     * @param msg     消息
     * @return 是否成功发送 这个好像无用
     */
    String sendMessageByAccount(String type, Long account, String msg);
}
