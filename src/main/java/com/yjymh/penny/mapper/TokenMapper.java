package com.yjymh.penny.mapper;

import com.yjymh.penny.entity.Token;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {
    Token queryTokenById(Token token);

    Token queryTokenByToken(Token token);

    int addToken(Token token);

    int updateToken(Token token);

    int deleteToken(Token token);
}
