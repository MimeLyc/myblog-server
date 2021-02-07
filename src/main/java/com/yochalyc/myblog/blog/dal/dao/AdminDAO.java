package com.yochalyc.myblog.blog.dal.dao;

import com.yochalyc.myblog.blog.dal.model.AdminDO;
import org.springframework.data.repository.CrudRepository;

public interface AdminDAO extends CrudRepository<AdminDO, Integer> {

    public AdminDO findFirstByAdminName(String adminName);

}

