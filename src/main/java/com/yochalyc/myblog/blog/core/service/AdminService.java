package com.yochalyc.myblog.blog.core.service;

import com.yochalyc.myblog.blog.dal.model.AdminDO;

public interface AdminService {

    public AdminDO login(String adminName, String password);
}
