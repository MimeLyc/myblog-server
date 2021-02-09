package com.yochalyc.myblog.blog.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Token {

    /**
     * token
     */
    private String accessToken;

    /**
     * token expire time
     */
    private Date tokenExpiresIn;

    /**
     * expire interval
     */
    private Integer exp;

}
