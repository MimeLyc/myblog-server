package com.yochalyc.myblog.blog.dal.dao;

import com.yochalyc.myblog.blog.dal.model.AdminDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AdminDAO extends BaseDAO<AdminDO, Long> {

    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.isDeleted = false and e.adminName = ?1")
    public AdminDO findByAdminName(String adminName);

}

