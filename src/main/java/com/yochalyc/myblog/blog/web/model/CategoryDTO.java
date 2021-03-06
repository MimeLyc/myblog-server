package com.yochalyc.myblog.blog.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CategoryDTO {

    private String id;

    private String name;

    private Boolean canDelete;

    private Date createTime;

    private Date updateTime;

    private Integer articleCount;

    private Boolean isDeleted;
}
