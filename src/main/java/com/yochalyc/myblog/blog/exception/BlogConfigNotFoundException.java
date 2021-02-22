package com.yochalyc.myblog.blog.exception;

public class BlogConfigNotFoundException extends BaseException {

    public BlogConfigNotFoundException() {
        super(ErrorCode.BLOG_CONFIG_NOT_FOUND);
    }

}
