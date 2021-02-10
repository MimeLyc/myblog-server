package com.yochalyc.myblog.blog.web.controller.admin;

import com.yochalyc.myblog.blog.core.service.ArticleService;
import com.yochalyc.myblog.blog.dal.enums.ArticleStatus;
import com.yochalyc.myblog.blog.dal.model.ArticleDO;
import com.yochalyc.myblog.blog.web.model.ArticleConditionVO;
import com.yochalyc.myblog.blog.web.model.ArticleListDTO;
import com.yochalyc.myblog.blog.web.model.ArticleOpRequest;
import com.yochalyc.myblog.blog.web.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

import static com.yochalyc.myblog.blog.config.GlobalProperties.DEFAULT_PAGE_SIZE;
import static com.yochalyc.myblog.blog.config.GlobalProperties.MAX_PAGE_SIZE;

@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/list/status")
    @ResponseBody
    public Result<ArticleListDTO> getListsByStatus(ArticleStatus status, int page, int pageSize) {
        page = getPage(page);
        pageSize = getPageSize(pageSize);

        try {
            List<ArticleDO> articleList = articleService.getListByStatus(status, page, pageSize);

            ArticleListDTO result = ArticleListDTO.builder()
                    .page(page)
                    .pageSize(pageSize)
                    .count(articleList.size())
                    .list(articleList.stream()
                            .map(ArticleConditionVO::new)
                            .collect(Collectors.toList())
                    ).build();
            return new Result<>(result);
        } catch (Exception e) {
            return new Result<>("", e.getMessage());
        }

    }

    @PostMapping("")
    @ResponseBody
    public Result<Void> save(ArticleOpRequest request) {
        return new Result<>(null);
    }


    private int getPage(int page) {
        return page < 0 ? 0 : page;
    }

    private int getPageSize(int pageSize) {
        return pageSize < 0 || pageSize > MAX_PAGE_SIZE ? DEFAULT_PAGE_SIZE : pageSize;
    }
}
