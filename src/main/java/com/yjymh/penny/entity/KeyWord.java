package com.yjymh.penny.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author yjymh
 */
@Data
public class KeyWord {
    // TODO: 2021/5/10

    private Long id;
    private String key;
    private String word;
    private Long group;
    private Long member;
    private boolean status;
    private Date update_time;
    private Date create_time;
}
