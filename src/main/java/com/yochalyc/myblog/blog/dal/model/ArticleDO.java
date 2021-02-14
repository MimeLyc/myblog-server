package com.yochalyc.myblog.blog.dal.model;

import com.yochalyc.myblog.blog.dal.enums.ArticleStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "article", indexes = @Index(name = "id_articleId", columnList = "uid"))
public class ArticleDO extends BaseDO {

    private String title;

    @ManyToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "category_id", referencedColumnName = "uid", insertable = false, updatable = false),
            @JoinColumn(name = "category_name", referencedColumnName = "name", insertable = false, updatable = false)
    })
    private CategoryDO category;

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

    @ManyToMany
    @JoinTable(
            name = "article_tag",
            joinColumns = {
                    @JoinColumn(name = "article_id", referencedColumnName = "uid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tag_id", referencedColumnName = "uid")
            }

    )
    private Set<TagDO> tags;


}
