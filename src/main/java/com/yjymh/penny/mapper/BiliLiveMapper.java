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
     * 添加新up的关注
     *
     * @param biliLive 关注信息
     * @return int
     * @since 1.1.0
     */
    int addBiliLive(BiliLive biliLive);

    /**
     * 查询所有关注的up
     *
     * @return 所有关注的up信息列表
     * @since 1.1.0
     */
    ArrayList<BiliLive> queryBiliLiveList();

    /**
     * 通过uid查询up的关注信息
     *
     * @param uid up的uid
     * @return up的关注信息
     * @since 1.1.0
     */
    BiliLive queryBiliLiveByUid(Long uid);

    /**
     * 更新up的关注信息
     *
     * @param biliLive up的关注信息
     * @return int
     * @since 1.1.0
     */
    int updateBiliLive(BiliLive biliLive);

    /**
     * 删除up的关注信息
     *
     * @param uid up的uid
     * @return int
     * @since 1.1.0
     */
    int delBiliLive(long uid);

}
