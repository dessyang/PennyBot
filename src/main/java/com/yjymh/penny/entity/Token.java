package com.yjymh.penny.entity;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author yjymh
 */
public class Token {
    private Long account;
    private String type;
    private String token;
    private boolean status;
    private Date updateTime;
    private Date createTime;

    public Token() {
        this.updateTime = DateUtil.date();
        this.createTime = this.updateTime;
    }

    public Token(Long account, String type, String token, boolean status, Date updateTime, Date create_time) {
        this.account = account;
        this.type = type;
        this.token = token;
        this.status = status;
        this.updateTime = updateTime;
        this.createTime = create_time;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date update_time) {
        this.updateTime = update_time;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
