package com.yochalyc.myblog.blog.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class LoginResultDTO {

    private String userId;

    private Token token;

    private Date lastLoginTime;

    private String userName;

}
