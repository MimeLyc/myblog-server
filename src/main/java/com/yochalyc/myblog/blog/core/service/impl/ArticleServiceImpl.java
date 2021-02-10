package com.yochalyc.myblog.blog.core.service.impl;

import com.yochalyc.myblog.blog.core.service.ArticleService;
import com.yochalyc.myblog.blog.dal.dao.ArticleDAO;
import com.yochalyc.myblog.blog.dal.enums.ArticleStatus;
import com.yochalyc.myblog.blog.dal.model.ArticleDO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public @NonNull List<ArticleDO> getListByStatus(ArticleStatus status, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<ArticleDO> articlePage = articleDAO.findByStatus(status, pageRequest);

        return articlePage.getContent();
    }

}
