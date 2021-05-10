package com.yjymh.penny.service;

import com.yjymh.penny.entity.KeyWord;
import org.springframework.stereotype.Service;


public interface KeyWordService {

    boolean keyWordExits(String key, Long group);

    KeyWord queryWordByKey(String key, Long group);

    int createTable(Long group);

    int addKeyWord(KeyWord keyWord);

    int updateKeyWord(KeyWord keyWord);

    int deleteKeyWord(String key, Long group);
}
