package com.yjymh.robot.mapper;

import com.yjymh.robot.entity.FriendToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendTokenMapper {

    FriendToken queryFriendById(Long account);

    FriendToken queryFriendByToken(String token);

    int addFriend(FriendToken friend);

    int updateFriend(FriendToken friend);

    int deleteFriend(Long account);

}
