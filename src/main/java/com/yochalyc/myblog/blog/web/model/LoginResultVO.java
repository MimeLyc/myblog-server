package com.yochalyc.myblog.blog.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LoginResultVO {

    private String userId;

    private Token token;

    private Date lastLoginTime;

    private String userName;

}
