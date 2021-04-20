package com.yjymh.robot.mapper;

import com.yjymh.robot.entity.GroupToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupTokenMapper {

    GroupToken queryGroupById(long account);

    GroupToken queryGroupByToken(String token);

    int addGroup(GroupToken group);

    int updateGroup(GroupToken group);

    int deleteGroup(Long account);

}
