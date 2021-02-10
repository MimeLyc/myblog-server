package com.yochalyc.myblog.blog.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "category", indexes = @Index(name = "id_categoryId", columnList = "categoryId"))
public class CategoryDO extends BaseDO {

    @Column(unique = true)
    private String categoryId;

    private String name;

    private Date createTime;

    private Date updateTime;

    private Boolean canDelete;

    @OneToMany(targetEntity = ArticleDO.class, mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ArticleDO> articles;
}
