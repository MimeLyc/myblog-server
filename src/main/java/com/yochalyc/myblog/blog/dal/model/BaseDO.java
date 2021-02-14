package com.yochalyc.myblog.blog.dal.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 自定义的自然键
     */
    @Column(unique = true)
    private String uid;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Date dataCreatedTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date dataModifiedTime;

    @Column(nullable = true)
    private Date dataDeletedTime;

    private Boolean isDeleted = false;

}
