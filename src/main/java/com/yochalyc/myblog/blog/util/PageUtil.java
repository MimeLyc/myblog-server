package com.yochalyc.myblog.blog.util;

import static com.yochalyc.myblog.blog.config.GlobalProperties.DEFAULT_PAGE_SIZE;
import static com.yochalyc.myblog.blog.config.GlobalProperties.MAX_PAGE_SIZE;

public class PageUtil {

    public static int formatPage(int page) {
        return page < 0 ? 0 : page;
    }

    public static int formatPageSize(int pageSize) {
        return pageSize < 0 || pageSize > MAX_PAGE_SIZE ? DEFAULT_PAGE_SIZE : pageSize;
    }
}
