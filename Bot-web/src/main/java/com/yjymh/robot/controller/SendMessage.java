package com.yjymh.robot.controller;

import com.yjymh.robot.PennyBot;
import com.yjymh.robot.entity.FriendToken;
import com.yjymh.robot.entity.GroupToken;
import com.yjymh.robot.entity.Response;
import com.yjymh.robot.service.FriendTokenService;
import com.yjymh.robot.service.GroupTokenService;
import com.yjymh.robot.utils.ResponseUtil;
import net.mamoe.mirai.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessage {

    @Autowired
    private FriendTokenService friendTokenService;

    @Autowired
    private GroupTokenService groupTokenService;

    @RequestMapping(value = "/sendfriend")
    public String sendFriendMessage(@RequestParam("token") String token,
                                    @RequestParam("msg") String msg) {
        try {
            Bot bot = PennyBot.getBot();

            FriendToken friendToken = friendTokenService.queryFriendByToken(token);
            if (msg != null && token != null) {
                if (friendToken != null) {
                    Long id = friendToken.getAccount();
                    if (id != null) {
                        bot.getFriend(id).sendMessage(msg);
                        return ResponseUtil.setResponse(Response.SUCCESS);
                    }
                }
            }

            return ResponseUtil.setResponse(Response.TOKEN_FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.setResponse(Response.FAIL);
        }
    }

    @RequestMapping(value = "/sendgroup")
    public String sendGroupMessage(@RequestParam("token") String token,
                                   @RequestParam("msg") String msg) {
        try {
            Bot bot = PennyBot.getBot();

            GroupToken groupToken = groupTokenService.queryGroupByToken(token);

            if (msg != null && token != null) {
                if (groupToken != null) {
                    Long group = groupToken.getAccount();
                    if (group != null) {
                        bot.getGroup(group).sendMessage(msg);
                        return ResponseUtil.setResponse(Response.SUCCESS);
                    }
                }
            }
            return ResponseUtil.setResponse(Response.TOKEN_FAIL);
        } catch (Exception e) {
            return ResponseUtil.setResponse(Response.FAIL);
        }
    }

}