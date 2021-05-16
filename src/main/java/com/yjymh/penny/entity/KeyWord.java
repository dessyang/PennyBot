package com.yjymh.penny.entity;

import lombok.Data;

/**
 * @author yjymh
 */
@Data
public class KeyWord {
    private Long id;
    private String key;
    private String word;
    private Long group;
    private Long member;
    private Boolean status;
    private String updateTime;
    private String createTime;
}
