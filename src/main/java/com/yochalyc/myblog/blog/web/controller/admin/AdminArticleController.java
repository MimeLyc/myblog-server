package com.yochalyc.myblog.blog.web.controller.admin;

import com.yochalyc.myblog.blog.core.converter.ArticleConverter;
import com.yochalyc.myblog.blog.core.service.ArticleService;
import com.yochalyc.myblog.blog.dal.enums.ArticleStatus;
import com.yochalyc.myblog.blog.dal.model.ArticleDO;
import com.yochalyc.myblog.blog.exception.BaseException;
import com.yochalyc.myblog.blog.web.controller.enums.ArticleQueryByEnum;
import com.yochalyc.myblog.blog.web.model.ArticleDTO;
import com.yochalyc.myblog.blog.web.model.ArticleListDTO;
import com.yochalyc.myblog.blog.web.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.yochalyc.myblog.blog.config.GlobalProperties.DEFAULT_PAGE_SIZE;
import static com.yochalyc.myblog.blog.config.GlobalProperties.MAX_PAGE_SIZE;

@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/list")
    @ResponseBody
    public Result<ArticleListDTO> getLists(ArticleQueryByEnum by, ArticleStatus status,
                                           int page, int pageSize) {
        page = getPage(page);
        pageSize = getPageSize(pageSize);

        try {
            List<ArticleDO> articleList = new ArrayList<>();

            switch (by) {
                case STATUS:
                    articleList = articleService.getListByStatus(status, page, pageSize);
                    break;
                default:
                    break;
            }

            ArticleConverter converter = new ArticleConverter();

            ArticleListDTO result = ArticleListDTO.builder()
                    .page(page)
                    .pageSize(pageSize)
                    .count(articleList.size())
                    .list(converter.createFromEntities(articleList)
                    ).build();
            return new Result<>(result);
        } catch (Exception e) {
            return new Result<>("", e.getMessage());
        }

    }

    @PostMapping("")
    @ResponseBody
    public Result<String> save(@RequestBody ArticleDTO request) {
        try {
            String articleId = articleService.save(request);
            return new Result<>(articleId);
        } catch (BaseException e) {
            return new Result<>(e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            return new Result<>("", e.getMessage());
        }
    }


    private int getPage(int page) {
        return page < 0 ? 0 : page;
    }

    private int getPageSize(int pageSize) {
        return pageSize < 0 || pageSize > MAX_PAGE_SIZE ? DEFAULT_PAGE_SIZE : pageSize;
    }
}
