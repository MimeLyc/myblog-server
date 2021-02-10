package com.yochalyc.myblog.blog.dal.model;

import com.yochalyc.myblog.blog.dal.enums.ArticleStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "article", indexes = @Index(name = "id_articleId", columnList = "articleId"))
public class ArticleDO extends BaseDO {

    @Column(unique = true)
    private String articleId;

    private String title;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId", insertable = false, updatable = false)
    private CategoryDO category;

    private Date createTime;

    private Date deleteTime;

    private Date updateTime;

    private Date publishTime;

    private ArticleStatus status;

    @Lob
    @Column(columnDefinition = "text")
    private String content;

    @Lob
    @Column(columnDefinition = "text")
    private String htmlContent;

    @Lob
    @Column(columnDefinition = "text")
    private String summary;

    private Long pv;

    private Boolean isEncrypt = false;


}
