package com.yochalyc.myblog.blog.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "tag", indexes = @Index(name = "id_tagId", columnList = "uid"))
public class TagDO extends BaseDO {

    private String name;

    @ManyToMany(targetEntity = ArticleDO.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "article_tag",
            joinColumns = {
                    @JoinColumn(name = "tag_id",referencedColumnName = "uid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "article_id",referencedColumnName = "uid")
            }

    )
    private Set<ArticleDO> articles;

    private Boolean isUserDeleted = false;

}
