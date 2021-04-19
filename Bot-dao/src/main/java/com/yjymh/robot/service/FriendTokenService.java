package com.yjymh.robot.service;

import com.yjymh.robot.entity.FriendToken;

public interface FriendTokenService {

    FriendToken queryFriendById(Long account);

    FriendToken queryFriendByToken(String token);

    int addFriend(FriendToken friend);

    int updateFriend(FriendToken friend);

    int deleteFriend(Long account);

}
