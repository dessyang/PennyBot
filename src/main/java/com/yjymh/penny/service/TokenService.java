package com.yjymh.penny.service;

import com.yjymh.penny.entity.Token;

public interface TokenService {
    Token queryTokenById(Token token);

    Token queryTokenByToken(Token token);

    int addToken(Token token);

    int updateToken(Token token);

    int deleteToken(Token account);

    Token getTokenInfo(String type, String token);

    String sendMessageByAccount(String type, Long account, String msg);
}
