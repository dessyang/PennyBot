package com.yjymh.robot.entity;

import java.util.Date;

public class KeyWord {

    private Long id;
    private String key;
    private String word;
    private Long group;
    private Long member;
    private boolean status;
    private Date update_time;
    private Date create_time;

    public KeyWord() {
    }

    public KeyWord(Long id, String key, String word, Long group, Long member, boolean status, Date update_time, Date create_time) {
        this.id = id;
        this.key = key;
        this.word = word;
        this.group = group;
        this.member = member;
        this.status = status;
        this.update_time = update_time;
        this.create_time = create_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public Long getMember() {
        return member;
    }

    public void setMember(Long member) {
        this.member = member;
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
