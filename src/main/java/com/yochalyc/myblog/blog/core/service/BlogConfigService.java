package com.yochalyc.myblog.blog.core.service;

import com.yochalyc.myblog.blog.dal.model.BlogConfigDO;
import com.yochalyc.myblog.blog.web.model.SetBlogConfigRequest;

public interface BlogConfigService {

    public BlogConfigDO setConfig(SetBlogConfigRequest request);

}
