package com.yjymh.penny.mapper;

import com.yjymh.penny.entity.BiliLive;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface BiliLiveMapper {

    ArrayList<BiliLive> queryLiveId();

    BiliLive queryBiliLiveByUid(Long uid);

    int addBiliLive(BiliLive biliLive);

    int updateBiliLive(BiliLive biliLive);

    int delBiliLive(long uid);

}
