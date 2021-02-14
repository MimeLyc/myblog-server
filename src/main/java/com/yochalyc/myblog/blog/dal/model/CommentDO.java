package com.yochalyc.myblog.blog.dal.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "comment", indexes = @Index(name = "id_commentId", columnList = "uid"))
public class CommentDO extends BaseDO {

    private String name;

    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "parent_comment_id", referencedColumnName = "uid")
    private CommentDO parentComment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "reply_comment_id", referencedColumnName = "uid")
    private CommentDO replyComment;

    private ArticleDO article;

    private String contentJson;

    private String rawContent;

    private String 


}
