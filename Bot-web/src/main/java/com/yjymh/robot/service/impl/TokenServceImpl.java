package com.yjymh.robot.service.impl;

import com.yjymh.robot.PennyBot;
import com.yjymh.robot.entity.Response;
import com.yjymh.robot.entity.Token;
import com.yjymh.robot.service.FriendTokenService;
import com.yjymh.robot.service.GroupTokenService;
import com.yjymh.robot.service.TokenService;
import com.yjymh.robot.utils.ResponseUtil;
import net.mamoe.mirai.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServceImpl implements TokenService {

    @Autowired
    private GroupTokenService groupTokenService;

    @Autowired
    private FriendTokenService friendTokenService;

    @Override
    public Token getTokenInfo(String type, String token) {
        Token tokenInfo = null;
        switch (type) {
            case "friend":
                tokenInfo = friendTokenService.queryFriendByToken(token);
                break;
            case "group":
                tokenInfo = groupTokenService.queryGroupByToken(token);
                break;
        }
        return tokenInfo;
    }

    @Override
    public String sendMessageByAccount(String type, Long account, String msg) {
        Bot bot = PennyBot.getBot();
        switch (type) {
            case "friend":
                bot.getFriend(account).sendMessage(msg);
                break;
            case "group":
                bot.getGroup(account).sendMessage(msg);
                break;
        }
        return ResponseUtil.setResponse(Response.SUCCESS);
    }
}
