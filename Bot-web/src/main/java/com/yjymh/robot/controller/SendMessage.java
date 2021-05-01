package com.yjymh.robot.controller;

import com.yjymh.robot.entity.Response;
import com.yjymh.robot.entity.Token;
import com.yjymh.robot.service.TokenService;
import com.yjymh.robot.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessage {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/send")
    public String sendMessage(String type,
                              String token,
                              String msg) {

        String response = null;

        if (!type.equals("") && !token.equals("") && !msg.equals("")) {

            Token tokenInfo = tokenService.getTokenInfo(type, token);

            if (tokenInfo != null) {
                Long account = tokenInfo.getAccount();
                if (account != null) {
                    response = tokenService.sendMessageByAccount(type, account, msg);
                }
            } else {
                response = ResponseUtil.setResponse(Response.TOKEN_FAIL);
            }
        } else {
            response = ResponseUtil.setResponse(Response.PARAM_FAIL);
        }
        return response;
    }
}