package com.yjymh.penny.entity;

/**
 * @author yjymh
 */
public enum Response {
    // TODO: 2021/5/10
    SUCCESS(0, "发送成功"),
    TOKEN_FAIL(1, "Token错误"),
    FAIL(2, "错误"),
    PARAM_FAIL(3, "参数错误");

    private int code;
    private String desc;

    Response(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}