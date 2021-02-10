package com.yochalyc.myblog.blog.web.model;

import lombok.Data;

@Data
public class ArticleDTO {

    private String id;

    private String title;

    private CategoryInfo category;

    private String summary;

    private String content;

    private String htmlContent;

    private Boolean isEncrypt = false;
}
