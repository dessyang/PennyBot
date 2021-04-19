package com.yjymh.robot.entity;

public enum Response {

    SUCCESS(0, "发送成功"),
    TOKEN_FAIL(1, "Token错误"),
    FAIL(2, "错误");

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