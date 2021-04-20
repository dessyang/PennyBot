package com.yjymh.robot.service;

import com.yjymh.robot.entity.KeyWord;

public interface KeyWordService {

    boolean keyWordExits(String key, Long group);

    KeyWord queryWordByKey(String key, Long group);

    int createTable(Long group);

    int addKeyWord(KeyWord keyWord);

    int updateKeyWord(KeyWord keyWord);

    int deleteKeyWord(String key, Long group);
}
