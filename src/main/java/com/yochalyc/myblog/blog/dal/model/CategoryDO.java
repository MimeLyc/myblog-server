package com.yochalyc.myblog.blog.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "category", indexes = @Index(name = "id_categoryId", columnList = "uid"))
public class CategoryDO extends BaseDO {

    private String name;

    private Boolean canDelete = true;

    @OneToMany(targetEntity = ArticleDO.class, mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ArticleDO> articles;
}
