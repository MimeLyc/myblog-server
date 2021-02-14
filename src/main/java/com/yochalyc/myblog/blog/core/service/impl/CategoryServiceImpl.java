package com.yochalyc.myblog.blog.core.service.impl;

import com.yochalyc.myblog.blog.core.service.CategoryService;
import com.yochalyc.myblog.blog.dal.dao.CategoryDAO;
import com.yochalyc.myblog.blog.dal.model.CategoryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public String add(String name, Boolean canDelete) {
        CategoryDO existedOne = categoryDAO.findByName(name);
        if (Objects.nonNull(existedOne)) {
            return existedOne.getUid();
        }

        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setName(name);
        categoryDO.setCanDelete(canDelete);

        return categoryDAO.saveWithoutId(categoryDO);
    }

    @Override
    public int count() {
        return 0;
    }

}
