package com.yochalyc.myblog.blog.exception;

public class ArticleNotFoundException extends BaseException {

    public ArticleNotFoundException() {
        super(ErrorCode.ADMIN_ARTICLE_NOT_FOUND);
    }

}
