package com.yochalyc.myblog.blog.dal.dao;

import com.yochalyc.myblog.blog.dal.model.CategoryDO;
import com.yochalyc.myblog.blog.util.Md5Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;


public interface CategoryDAO extends BaseDAO<CategoryDO, Long> {

    public CategoryDO findByName(String name);

    @Override
    default String saveWithoutId(CategoryDO entity) {
        String uid = Md5Util.randomToken_16();

        entity.setUid(uid);

        save(entity);

        return uid;
    }

    default List<CategoryDO> findAllOrderByName(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize,
                Sort.by(Sort.Direction.ASC, "name")
        );
        Page<CategoryDO> categorys = findByPage(pageRequest);

        return categorys.getContent();
    }

}
