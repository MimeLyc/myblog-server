package com.yochalyc.myblog.blog.core.service;

import com.yochalyc.myblog.blog.dal.enums.ArticleStatus;
import com.yochalyc.myblog.blog.dal.model.ArticleDO;

import java.util.List;

public interface ArticleService {

    public List<ArticleDO> getListByStatus(ArticleStatus status, int page, int pageSize);

}
