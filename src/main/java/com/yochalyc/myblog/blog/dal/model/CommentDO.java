package com.yochalyc.myblog.blog.dal.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "comment", indexes = @Index(name = "id_commentId", columnList = "uid"))
public class CommentDO extends BaseDO {

    private String name;

    private String email;

    @ManyToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "article_id", referencedColumnName = "uid")
    })
    private ArticleDO article;

    @Lob
    @Column(columnDefinition = "text")
    private String contentJson;

    @Lob
    @Column(columnDefinition = "text")
    private String rawContent;

    private Boolean isAuthor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id", referencedColumnName = "uid")
    private CommentDO parentComment;

    @OneToMany(targetEntity = CommentDO.class, mappedBy = "parentComment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CommentDO> childrenComments;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_comment_id", referencedColumnName = "uid")
    private CommentDO replyComment;

    @OneToMany(targetEntity = CommentDO.class, mappedBy = "replyComment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CommentDO> referComments;

}
