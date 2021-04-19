package com.yjymh.robot.mapper;

import com.yjymh.robot.entity.GroupToken;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GroupTokenMapper {
    @Select("select * from `group` where account = ${account}")
    GroupToken queryGroupById(long account);

    @Select("select * from `group` where token = '${token}'")
    GroupToken queryGroupByToken(String token);

    @Insert("INSERT INTO `group` (`account`, `token`, `status`, `update_time`, `create_time`) VALUES (#{account}, #{token}, #{status}, #{update_time}, #{create_time})")
    int addGroup(GroupToken group);

    @Update("UPDATE `group` SET `token` = '${token}', `update_time` = '${update_time}' WHERE account = ${account}")
    int updateGroup(GroupToken group);

    @Delete("DELETE FROM `group` WHERE account = ${group}")
    int deleteGroup(Long account);
}
