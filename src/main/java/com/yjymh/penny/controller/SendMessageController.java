package com.yjymh.penny.controller;

import com.yjymh.penny.constant.Const;
import com.yjymh.penny.entity.Response;
import com.yjymh.penny.entity.Token;
import com.yjymh.penny.service.TokenService;
import com.yjymh.penny.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yjymh
 */
@RestController
public class SendMessageController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/send")
    public String sendMessage(String type,
                              String token,
                              String msg) {

        String response = null;

        if (!Const.EMPTY.equals(type) && !token.equals("") && !msg.equals("")) {

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