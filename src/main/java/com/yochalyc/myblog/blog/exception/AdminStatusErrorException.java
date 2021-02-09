package com.yochalyc.myblog.blog.exception;

public class AdminStatusErrorException extends BaseException {

    public AdminStatusErrorException() {
        super(ErrorCode.ADMIN_STATUS_ABNORMAL);
    }

}
