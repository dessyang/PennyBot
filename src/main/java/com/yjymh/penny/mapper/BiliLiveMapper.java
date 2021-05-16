package com.yjymh.penny.mapper;

import com.yjymh.penny.entity.BiliLive;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * @author yjymh
 */
@Mapper
public interface BiliLiveMapper {

    /**
     * 查询所有关注的直播
     * @return
     */
    ArrayList<BiliLive> queryLiveId();

    /**
     * 使用uid查询
     * @param uid
     * @return
     */
    BiliLive queryBiliLiveByUid(Long uid);

    int addBiliLive(BiliLive biliLive);

    int updateBiliLive(BiliLive biliLive);

    int delBiliLive(long uid);

}
