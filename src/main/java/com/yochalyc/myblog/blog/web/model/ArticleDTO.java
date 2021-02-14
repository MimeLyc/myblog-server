package com.yochalyc.myblog.blog.web.model;

import com.yochalyc.myblog.blog.dal.enums.ArticleStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleDTO {

    private String id;

    private String title;

    private CategoryDTO category;

    private String summary;

    private String content;

    private String htmlContent;

    private Boolean isEncrypt = false;

    private Date createTime;

    private Date deleteTime;

    private Date updateTime;

    private Date publishTime;

    private ArticleStatus status;

    private Long pv;
}
