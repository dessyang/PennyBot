package com.yjymh.robot.service;

import com.yjymh.robot.entity.GroupToken;

public interface GroupTokenService {

    GroupToken queryGroupById(long account);

    GroupToken queryGroupByToken(String token);

    int addGroup(GroupToken group);

    int updateGroup(GroupToken group);

    int deleteGroup(Long account);

}
