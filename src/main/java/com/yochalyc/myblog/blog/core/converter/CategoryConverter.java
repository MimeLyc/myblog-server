package com.yochalyc.myblog.blog.core.converter;

import com.yochalyc.myblog.blog.dal.model.CategoryDO;
import com.yochalyc.myblog.blog.web.model.CategoryDTO;

public class CategoryConverter extends Converter<CategoryDTO, CategoryDO> {

    public CategoryConverter() {
        super(CategoryConverter::convertToEntity, CategoryConverter::convertToDTO);
    }

    private static CategoryDO convertToEntity(CategoryDTO categoryDTO) {
        CategoryDO categoryDO = new CategoryDO();

        categoryDO.setUid(categoryDTO.getId());
        categoryDO.setName(categoryDTO.getName());
        categoryDO.setDataCreatedTime(categoryDTO.getCreateTime());
        categoryDO.setDataModifiedTime(categoryDTO.getUpdateTime());
        categoryDO.setIsUserDeleted(categoryDTO.getIsDeleted());
        categoryDO.setCanDelete(categoryDTO.getCanDelete());

        return categoryDO;
    }

    private static CategoryDTO convertToDTO(CategoryDO categoryDO) {
        return new CategoryDTO(categoryDO.getUid(), categoryDO.getName(),
                categoryDO.getCanDelete(), categoryDO.getDataCreatedTime(),
                categoryDO.getDataModifiedTime(), categoryDO.getArticles().size(),
                categoryDO.getIsUserDeleted());
    }

}
