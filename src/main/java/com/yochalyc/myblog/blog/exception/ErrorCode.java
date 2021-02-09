package com.yochalyc.myblog.blog.exception;


public enum ErrorCode {

    ADMIN_NOT_FOUND(10000, "用户名或密码错误"),
    ADMIN_PWD_ERROR(10001, "密码错误"),
    ADMIN_STATUS_ABNORMAL(10002, "用户状态异常");


    private Integer code;
    private String desc;

    private ErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
