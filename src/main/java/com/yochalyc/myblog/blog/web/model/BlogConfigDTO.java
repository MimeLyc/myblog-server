package com.yochalyc.myblog.blog.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlogConfigDTO {

    private String blogName;

    private String avatar;

    private String sign;

    private String github;

}
