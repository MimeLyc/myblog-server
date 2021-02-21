package com.yochalyc.myblog.blog.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CommentDTO {

    private String id;

    private String name;

    private String articleTitle;

    private String content;

    private Boolean isAuthor;

    private String parentId;

    private String replyId;

    private Date createTime;

    private Boolean isDeleted;
}
