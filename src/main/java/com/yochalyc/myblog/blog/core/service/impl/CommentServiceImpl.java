package com.yochalyc.myblog.blog.core.service.impl;

import com.yochalyc.myblog.blog.core.service.CommentService;
import com.yochalyc.myblog.blog.dal.dao.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public Integer count() {
        return Math.toIntExact(commentDAO.count());
    }
}
