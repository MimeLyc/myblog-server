package com.yochalyc.myblog.blog.dal.dao;

import com.yochalyc.myblog.blog.dal.model.CommentDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommentDAO extends BaseDAO<CommentDO, Long> {

    default List<CommentDO> findAllOrderByCreateTime(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize,
                Sort.by(Sort.Direction.DESC, "dataCreatedTime")
        );
        Page<CommentDO> comments = findByPage(pageRequest);

        return comments.getContent();
    }

}
