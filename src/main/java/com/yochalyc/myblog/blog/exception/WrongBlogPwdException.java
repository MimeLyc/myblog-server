package com.yochalyc.myblog.blog.exception;

public class WrongBlogPwdException extends BaseException {

    public WrongBlogPwdException() {
        super(ErrorCode.BLOG_PWD_WRONG);
    }

}
