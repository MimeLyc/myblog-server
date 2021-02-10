package com.yochalyc.myblog.blog.dal.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Date dataCreatedTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date dataModifiedTime;

    private Boolean isDeleted = false;

}
