package com.yjymh.robot.entity;

import java.util.Date;


public class GroupToken extends Token {

    public GroupToken(Long account, String token, boolean status, Date update_time, Date create_time) {
        super(account, token, status, update_time, create_time);
    }

    public GroupToken() {
    }

}
