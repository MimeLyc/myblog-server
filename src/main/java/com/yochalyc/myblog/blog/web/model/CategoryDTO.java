package com.yochalyc.myblog.blog.web.model;

import lombok.Data;

@Data
public class CategoryDTO {

    private String id;

    private String name;

    private Boolean canDelete;
}
