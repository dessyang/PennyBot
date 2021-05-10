package com.yjymh.penny.entity;

import cn.hutool.core.date.DateUtil;
import com.yjymh.penny.requests.service.impl.BiliRequestServiceImpl;

import java.util.Date;

public class BiliLive {
    private Long uid;
    private Long liveId;
    private String name;
    private boolean liveStatus;
    private String starGroup;
    private Date createTime;
    private Date updateTime;

    public BiliLive(long uid) {

        BiliRequestServiceImpl biliRequestService = new BiliRequestServiceImpl();
        this.uid = uid;
        this.liveId = biliRequestService.getRoomIdByUid(uid);
        this.liveStatus = biliRequestService.getLiveStateByUid(uid);
        this.name = biliRequestService.getName(uid);
        this.createTime = DateUtil.date();
        this.updateTime = this.createTime;
    }

    public BiliLive(Long uid, Long liveId, String name, boolean liveStatus, String starGroup, Date createTime, Date updateTime) {
        this.uid = uid;
        this.liveId = liveId;
        this.name = name;
        this.liveStatus = liveStatus;

        this.starGroup = starGroup;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getLiveId() {
        return liveId;
    }

    public void setLiveId(Long liveId) {
        this.liveId = liveId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(boolean liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getStarGroup() {
        return starGroup;
    }

    public void setStarGroup(String starGroup) {
        this.starGroup = starGroup;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
