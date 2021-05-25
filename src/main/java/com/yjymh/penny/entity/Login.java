package com.yjymh.penny.entity;

import lombok.Data;

/**
 * 登录的一些相关数据
 *
 * @author yjymh
 */
@Data
public class Login {
    /**
     * 登录密钥
     */
    private String oauthKey;
    /**
     * 需要扫码登录的链接
     */
    private String qrLoginUrl;
    /**
     * 密钥的开始时间
     * 单位是秒
     */
    private long startTime;
    /**
     * 密钥的过期时间
     */
    private long endTime;
    /**
     * 密钥的生命周期
     */
    private long lifeTime;

    public long getEndTime() {
        if (endTime == 0) {
            if (lifeTime == 0) {
                // 默认设置为180秒
                this.lifeTime = 180;
            }
            this.endTime = this.getStartTime() + this.lifeTime;
        }
        return endTime;
    }
}
