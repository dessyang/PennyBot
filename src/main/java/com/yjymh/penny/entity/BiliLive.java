package com.yjymh.penny.entity;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * @author yjymh
 */
@Data
public class BiliLive {
    private Long uid;
    private Long liveId;
    private String name;
    private boolean liveStatus;
    private String starFriend;
    private String starGroup;
    private Date createTime;
    private Date updateTime;

    public void setCreateTime(Date createTime) {
        this.createTime = DateUtil.parse(DateUtil.formatDateTime(createTime));
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = DateUtil.parse(DateUtil.formatDateTime(updateTime));
    }
}
