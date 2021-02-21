package com.yochalyc.myblog.blog.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TagDTO {

    private String tagId;

    private String tagName;

    private Date createTime;

    private Date updateTime;

    private Integer articleCount;

    private Boolean isDeleted;

}
