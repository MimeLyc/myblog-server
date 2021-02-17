package com.yochalyc.myblog.blog.dal.dao;


import com.yochalyc.myblog.blog.dal.enums.ArticleStatus;
import com.yochalyc.myblog.blog.dal.model.ArticleDO;
import com.yochalyc.myblog.blog.util.Md5Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleDAO extends BaseDAO<ArticleDO, Long> {

    @Transactional(readOnly = true)
    @Query(value = "select e from #{#entityName} e " +
            "where e.isDeleted = false and e.uid <> -1"
    )
    Page<ArticleDO> findByStatus(ArticleStatus status, Pageable pageable);


    default List<ArticleDO> findByStatusOrOrderByPublishTime(ArticleStatus status, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize,
                Sort.by(Sort.Direction.DESC, "publishTime")
        );
        Page<ArticleDO> articlePage = findByStatus(status, pageRequest);

        return articlePage.getContent();
    }

    @Override
    @Transactional
    default String saveWithoutId(ArticleDO articleDO) {
        String articleId = Md5Util.randomToken_16();

        articleDO.setUid(articleId);
        articleDO.setStatus(ArticleStatus.DRAFT);

        save(articleDO);

        return articleId;
    }

    @Transactional
    default String saveWithId(ArticleDO articleDO) {
        if (articleDO.getStatus() == ArticleStatus.DELETED) {
            articleDO.setStatus(ArticleStatus.DRAFT);
        }

        save(articleDO);

        return articleDO.getUid();
    }

    @Query("SELECT COUNT(e) FROM #{#entityName} e WHERE e.status=?1 and e.isDeleted=false")
    long countByStatus(ArticleStatus status);

}
