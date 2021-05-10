package com.yjymh.penny.service.impl;

import com.yjymh.penny.bot.PennyBot;
import com.yjymh.penny.entity.Response;
import com.yjymh.penny.entity.Token;
import com.yjymh.penny.mapper.TokenMapper;
import com.yjymh.penny.service.TokenService;
import com.yjymh.penny.utils.ResponseUtil;
import net.mamoe.mirai.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenMapper tokenMapper;

    @Override
    public Token queryTokenById(Token token) {
        return tokenMapper.queryTokenById(token);
    }

    @Override
    public Token queryTokenByToken(Token token) {
        return tokenMapper.queryTokenByToken(token);
    }

    @Override
    public int addToken(Token token) {
        return tokenMapper.addToken(token);
    }

    @Override
    public int updateToken(Token token) {
        return tokenMapper.updateToken(token);
    }

    @Override
    public int deleteToken(Token account) {
        return tokenMapper.deleteToken(account);
    }


    @Override
    public Token getTokenInfo(String type, String token) {
        return queryTokenByToken(new Token() {{
            setType(type);
            setToken(token);
        }});
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
