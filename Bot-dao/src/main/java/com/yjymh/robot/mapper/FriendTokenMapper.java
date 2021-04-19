package com.yjymh.robot.mapper;

import com.yjymh.robot.entity.FriendToken;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FriendTokenMapper {

    @Select("select * from friend where account = ${account}")
    FriendToken queryFriendById(long account);

    @Select("select * from friend where token = '${token}'")
    FriendToken queryFriendByToken(String token);

    @Insert("INSERT INTO `friend` (`account`, `token`, `status`, `update_time`, `create_time`) VALUES (#{account}, #{token}, #{status}, #{update_time}, #{create_time})")
    int addFriend(FriendToken friend);

    @Update("UPDATE `friend` SET `token` = '${token}', `update_time` = '${update_time}' WHERE account = ${account}")
    int updateFriend(FriendToken friend);

    @Delete("DELETE FROM `friend` WHERE `account`=${account}")
    int deleteFriend(Long account);

}
