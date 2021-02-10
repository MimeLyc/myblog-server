package com.yochalyc.myblog.blog.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "admin", indexes = @Index(name = "id_name", columnList = "adminName"))
public class AdminDO extends BaseDO {
    @Column(unique = true)
    private String adminId;

    @Column(unique = true)
    private String adminName;

    private String password;

    private String salt;

    private String accessToken;

    private Date tokenExpireIn;

    private Boolean isHealth;

    private Date lastLoginTime;

    public AdminDO(){}

    public AdminDO(String adminName, String password) {
        this.adminName = adminName;
        this.password = password;
    }

}
