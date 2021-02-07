package com.yochalyc.myblog.blog.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "admin", indexes = @Index(name = "id_name", columnList = "adminName"))
public class AdminDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(unique = true)
    private String adminId;

    @Column(unique = true)
    private String adminName;

    private String password;

    private String salt;

    private String accessToken;

    private String tokenExpireIn;

    private Boolean isHealth;

    private Date lastLoginTime;

}