package com.yochalyc.myblog.blog.core.service.impl;

import com.yochalyc.myblog.blog.core.service.UserService;
import com.yochalyc.myblog.blog.dal.dao.UserDAO;
import com.yochalyc.myblog.blog.dal.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void register(UserDO userDO) {
        userDAO.save(userDO);
    }
}
