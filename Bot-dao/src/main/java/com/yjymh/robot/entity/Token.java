package com.yjymh.robot.entity;

import java.util.Date;

public class Token {

    private Long account;
    private String token;
    private boolean status;
    private Date update_time;
    private Date create_time;

    public Token(Long account, String token, boolean status, Date update_time, Date create_time) {
        this.account = account;
        this.token = token;
        this.status = status;
        this.update_time = update_time;
        this.create_time = create_time;
    }

    public Token() {
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

}
