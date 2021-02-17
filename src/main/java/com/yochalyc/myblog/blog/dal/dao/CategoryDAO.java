package com.yochalyc.myblog.blog.dal.dao;

import com.yochalyc.myblog.blog.dal.model.CategoryDO;
import com.yochalyc.myblog.blog.util.Md5Util;


public interface CategoryDAO extends BaseDAO<CategoryDO, Long> {

    public CategoryDO findByName(String name);

    @Override
    default String saveWithoutId(CategoryDO entity) {
        String uid = Md5Util.randomToken_16();

        entity.setUid(uid);

        save(entity);

        return uid;
    }

}
