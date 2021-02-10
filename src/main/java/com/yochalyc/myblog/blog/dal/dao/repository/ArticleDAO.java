package com.yochalyc.myblog.blog.dal.dao.repository;


import com.yochalyc.myblog.blog.dal.dao.BaseDAO;
import com.yochalyc.myblog.blog.dal.enums.ArticleStatus;
import com.yochalyc.myblog.blog.dal.model.ArticleDO;
import com.yochalyc.myblog.blog.util.Md5Util;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public interface ArticleDAO extends BaseDAO<ArticleDO, Long> {

    @Transactional(readOnly = true)
    @Query(value = "select e from #{#entityName} e " +
            "where e.isDeleted = false and e.articleId != -1" +
            "order by e.publishTime desc ",
            countQuery = "select count(*) from #{#entityName} e " +
                    "where e.isDeleted = false and e.articleId != -1",
            nativeQuery = true)
    Page<ArticleDO> findByStatus(ArticleStatus status, Pageable pageable);

    @Transactional
    default String saveWithoutId(ArticleDO articleDO) {
        String articleId = Md5Util.randomToken_16();

        articleDO.setArticleId(articleId);
        articleDO.setCreateTime(new Date());
        articleDO.setUpdateTime(new Date());
        articleDO.setStatus(ArticleStatus.DRAFT);

        save(articleDO);

        return articleId;
    }

    @Transactional
    default String saveWithId(ArticleDO articleDO) {
        articleDO.setDeleteTime(null);
        articleDO.setUpdateTime(new Date());
        if (articleDO.getStatus() == ArticleStatus.DELETED) {
            articleDO.setStatus(ArticleStatus.DRAFT);
        }

        save(articleDO);

        return articleDO.getArticleId();
    }

    public ArticleDO findByArticleId(String articleId);

}
