package com.yochalyc.myblog.blog.exception;

public class PwdValidationException extends BaseException {

    public PwdValidationException() {
        super(ErrorCode.ADMIN_PWD_ERROR);
    }

}
