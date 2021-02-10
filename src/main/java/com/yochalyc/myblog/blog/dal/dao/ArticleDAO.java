package com.yochalyc.myblog.blog.dal.dao;

import com.yochalyc.myblog.blog.dal.model.ArticleDO;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleDAO extends BaseDAO<ArticleDO, Long> {

    @Autowired
    private com.yochalyc.myblog.blog.dal.dao.repository.ArticleDAO articleRepository;

    @Override
    protected ArticleDO getEntity() {
        return null;
    }

    @Override
    protected com.yochalyc.myblog.blog.dal.dao.repository.ArticleDAO getRepository() {
        return articleRepository;
    }
}
