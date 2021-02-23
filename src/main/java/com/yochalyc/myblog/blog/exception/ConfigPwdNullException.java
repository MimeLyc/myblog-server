package com.yochalyc.myblog.blog.exception;

public class ConfigPwdNullException extends BaseException {

    public ConfigPwdNullException() {
        super(ErrorCode.BLOG_PWD_CAN_NOT_BE_NULL);
    }

}
