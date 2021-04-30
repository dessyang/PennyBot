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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessage {

    @Autowired
    private FriendTokenService friendTokenService;

    @Autowired
    private GroupTokenService groupTokenService;

    @PostMapping("/send")
    public String send(@RequestParam String type,
                       @RequestParam String token,
                       @RequestParam String msg) {
        Bot bot = PennyBot.getBot();
        String response = ResponseUtil.setResponse(Response.PARAM_FAIL);

        if (type.contains("group")) {
            try {
                if (msg != null && token != null) {
                    GroupToken groupToken = groupTokenService.queryGroupByToken(token);
                    if (groupToken != null) {
                        Long group = groupToken.getAccount();
                        if (group != null) {
                            bot.getGroup(group).sendMessage(msg);
                            return ResponseUtil.setResponse(Response.SUCCESS);
                        }
                    } else {
                        response = ResponseUtil.setResponse(Response.TOKEN_FAIL);
                    }
                } else {
                    response = ResponseUtil.setResponse(Response.PARAM_FAIL);
                }
            } catch (Exception e) {
                response = ResponseUtil.setResponse(Response.FAIL);
            }
        } else if (type.contains("friend")) {
            try {
                if (msg != null && token != null) {
                    FriendToken friendToken = friendTokenService.queryFriendByToken(token);
                    if (friendToken != null) {
                        Long id = friendToken.getAccount();
                        if (id != null) {
                            bot.getFriend(id).sendMessage(msg);
                            response = ResponseUtil.setResponse(Response.SUCCESS);
                        }
                    } else {
                        response = ResponseUtil.setResponse(Response.TOKEN_FAIL);
                    }
                } else {
                    response = ResponseUtil.setResponse(Response.PARAM_FAIL);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response = ResponseUtil.setResponse(Response.FAIL);
            }
        }
        return response;
    }
}