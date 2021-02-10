package com.yochalyc.myblog.blog.dal.dao;


import com.yochalyc.myblog.blog.dal.enums.ArticleStatus;
import com.yochalyc.myblog.blog.dal.model.ArticleDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface ArticleDAO extends BaseDAO<ArticleDO, Long> {

    @Transactional(readOnly = true)
    @Query(value = "select e from #{#entityName} e " +
            "where e.isDeleted = false and e.articleId != -1" +
            "order by e.publishTime desc ",
            countQuery = "select count(*) from #{#entityName} e " +
                    "where e.isDeleted = false and e.articleId != -1",
            nativeQuery = true)
    Page<ArticleDO> findByStatus(ArticleStatus status, Pageable pageable);
}
