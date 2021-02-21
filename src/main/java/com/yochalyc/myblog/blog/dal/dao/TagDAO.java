package com.yochalyc.myblog.blog.dal.dao;

import com.yochalyc.myblog.blog.dal.model.TagDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TagDAO extends BaseDAO<TagDO, Long> {

    default List<TagDO> findAllOrderByName(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize,
                Sort.by(Sort.Direction.ASC, "name")
        );
        Page<TagDO> tagDOPage = findByPage(pageRequest);

        return tagDOPage.getContent();
    }
}
