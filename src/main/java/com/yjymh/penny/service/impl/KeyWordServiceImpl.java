package com.yjymh.penny.service.impl;

import com.yjymh.penny.entity.KeyWord;
import com.yjymh.penny.mapper.KeyWordMapper;
import com.yjymh.penny.service.KeyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyWordServiceImpl implements KeyWordService {

    @Autowired
    KeyWordMapper keyWordMapper;

    @Override
    public boolean keyWordExits(String key, Long group) {
        boolean flag = false;
        KeyWord keyWord = keyWordMapper.queryWordByKey(key, group);
        if (keyWord != null) {
            flag = true;
        }
        return flag;
    }

    @Override
    public KeyWord queryWordByKey(String key, Long group) {
        return keyWordMapper.queryWordByKey(key, group);
    }

    @Override
    public int createTable(Long group) {
        return keyWordMapper.createTable(group);
    }

    @Override
    public int addKeyWord(KeyWord keyWord) {
        return keyWordMapper.addKeyWord(keyWord);
    }

    @Override
    public int updateKeyWord(KeyWord keyWord) {
        return keyWordMapper.updateKeyWord(keyWord);
    }

    @Override
    public int deleteKeyWord(String key, Long group) {
        return keyWordMapper.deleteKeyWord(key, group);
    }
}
