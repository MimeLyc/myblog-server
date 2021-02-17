package com.yochalyc.myblog.blog.web.model;

import com.yochalyc.myblog.blog.dal.enums.ArticleStatus;
import com.yochalyc.myblog.blog.dal.model.ArticleDO;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleConditionVO {

    private String articleId;

    private String title;

    private String categoryName;

    private Date createTime;

    private Date deleteTime;

    private Date updateTime;

    private Date publishTime;

    private ArticleStatus status;

    private Long pv;

    private Boolean isEncrypt;

    public ArticleConditionVO(ArticleDO articleDO) {
        this.articleId = articleDO.getUid();
        this.title = articleDO.getTitle();
        this.categoryName = articleDO.getCategory().getName();
        this.createTime = articleDO.getDataCreatedTime();
        this.deleteTime = articleDO.getDataDeletedTime();
        this.updateTime = articleDO.getDataModifiedTime();
        this.publishTime = articleDO.getPublishTime();
        this.status = articleDO.getStatus();
        this.pv = articleDO.getPv();
        this.isEncrypt = articleDO.getIsEncrypt();
    }
}
