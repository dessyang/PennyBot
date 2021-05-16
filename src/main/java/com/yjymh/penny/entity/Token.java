package com.yjymh.penny.entity;

import lombok.Data;

/**
 * @author yjymh
 */
@Data
public class Token {
    private Long account;
    private String type;
    private String token;
    private Boolean status;
    private String updateTime;
    private String createTime;
}
