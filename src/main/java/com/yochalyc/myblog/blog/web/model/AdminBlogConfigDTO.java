package com.yochalyc.myblog.blog.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminBlogConfigDTO {

    private String blogName;

    private String avatar;

    private String sign;

    private String wxpayQrcode;

    private String alipayQrcode;

    private String github;

    private Boolean hadOldPassword;

}
