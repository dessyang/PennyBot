package com.yjymh.robot.mapper;

import com.yjymh.robot.entity.KeyWord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KeyWordMapper {

    KeyWord queryWordByKey(String key, Long group);

    // 创建与群名相同的表 保存群关键词配置
    int createTable(Long group);

    // 增加关键词
    int addKeyWord(KeyWord keyWord);

    int updateKeyWord(KeyWord keyWord);

    int deleteKeyWord(String key, Long group);

}
