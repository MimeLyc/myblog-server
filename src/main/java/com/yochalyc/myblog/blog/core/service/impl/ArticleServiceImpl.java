package com.yochalyc.myblog.blog.core.service.impl;

import com.yochalyc.myblog.blog.core.converter.ArticleConverter;
import com.yochalyc.myblog.blog.core.service.ArticleService;
import com.yochalyc.myblog.blog.dal.dao.ArticleDAO;
import com.yochalyc.myblog.blog.dal.enums.ArticleStatus;
import com.yochalyc.myblog.blog.dal.model.ArticleDO;
import com.yochalyc.myblog.blog.exception.ArticleNotFoundException;
import com.yochalyc.myblog.blog.web.model.ArticleDTO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public @NonNull List<ArticleDO> getListByStatus(ArticleStatus status,
                                                    int page, int pageSize) {
        return articleDAO.findByStatusOrOrderByPublishTime(status, page, pageSize);
    }

    @Override
    public String save(ArticleDTO request) {
        ArticleConverter converter = new ArticleConverter();
        ArticleDO articleDO = converter.convertFromDto(request);

        String articleId = articleDO.getUid();
        if (Objects.isNull(articleId)) {
            articleId = articleDAO.saveWithoutId(articleDO);
        } else {
            ArticleDO isExist = articleDAO.findByUid(articleId);
            if (Objects.isNull(isExist)) {
                throw new ArticleNotFoundException();
            }

            articleDO.setId(isExist.getId());
            articleDO.setPv(isExist.getPv());

            articleId = articleDAO.saveWithId(articleDO);
        }


        return articleId;
    }

    @Override
    public Integer countByStatus(ArticleStatus status) {
        long count = articleDAO.countByStatus(status);
        return Math.toIntExact(count);
    }

}
