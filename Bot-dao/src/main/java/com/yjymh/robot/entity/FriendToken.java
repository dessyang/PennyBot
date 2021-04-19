package com.yjymh.robot.entity;

import java.util.Date;


public class FriendToken extends Token {

    public FriendToken(Long account, String token, boolean status, Date update_time, Date create_time) {
        super(account, token, status, update_time, create_time);
    }

    public FriendToken() {
    }

}
