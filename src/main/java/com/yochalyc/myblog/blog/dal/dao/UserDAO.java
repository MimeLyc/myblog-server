package com.yochalyc.myblog.blog.dal.dao;

import com.yochalyc.myblog.blog.dal.model.UserDO;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<UserDO, Integer> {
}
