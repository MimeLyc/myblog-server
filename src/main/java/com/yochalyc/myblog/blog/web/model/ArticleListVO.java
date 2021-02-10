package com.yochalyc.myblog.blog.web.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticleListVO {

    private Integer page;

    private Integer pageSize;

    private Integer count;

    private List<ArticleConditionVO> list;

}
