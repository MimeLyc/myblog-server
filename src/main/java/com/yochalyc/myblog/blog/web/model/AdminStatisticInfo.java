package com.yochalyc.myblog.blog.web.model;

import lombok.Data;

@Data
public class AdminStatisticInfo {

    private Integer publishArticleCount;
    private Integer deleteArticleCount;
    private Integer draftArticleCount;

    private Integer categoryCount;

    private Integer tagCount;

    private Integer commentCount;
}
