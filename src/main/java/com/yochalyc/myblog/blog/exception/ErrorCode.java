package com.yochalyc.myblog.blog.exception;


public enum ErrorCode {

    ADMIN_NOT_FOUND(10000, "用户名或密码错误"),
    ADMIN_PWD_ERROR(10001, "密码错误"),
    ADMIN_STATUS_ABNORMAL(10002, "用户状态异常"),

    ADMIN_ARTICLE_NOT_FOUND(20000, "文章不存在"),

    BLOG_CONFIG_NOT_FOUND(50000, "缺少博客配置信息"),
    BLOG_PWD_WRONG(50002, "博客密码错误"),
    BLOG_PWD_CAN_NOT_BE_NULL(50002, "博客密码不能为空");


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
