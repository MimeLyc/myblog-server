package com.yochalyc.myblog.blog.web.model;

import lombok.Data;

@Data
public class SetBlogConfigRequest {

    private String blogName;

    private String avatar;

    private String sign;

    private String wxpayQrcode;

    private String alipayQrcode;

    private String github;

    private Boolean isSetPwdRequest;

    private String oldPassword;

    private String viewPassword;


}
