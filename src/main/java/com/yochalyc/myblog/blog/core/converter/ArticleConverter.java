package com.yochalyc.myblog.blog.core.converter;

import com.yochalyc.myblog.blog.dal.model.ArticleDO;
import com.yochalyc.myblog.blog.dal.model.CategoryDO;
import com.yochalyc.myblog.blog.web.model.ArticleDTO;
import com.yochalyc.myblog.blog.web.model.CategoryDTO;

import java.util.Optional;

public class ArticleConverter extends Converter<ArticleDTO, ArticleDO> {

    public ArticleConverter() {
        super(ArticleConverter::convertToEntity, ArticleConverter::convertToDTO);
    }

    private static ArticleDO convertToEntity(ArticleDTO articleDTO) {
        ArticleDO articleDO = new ArticleDO();

        Optional<CategoryDTO> categoryInfo = Optional.ofNullable(articleDTO.getCategory());

        categoryInfo.ifPresent(
                item -> {
                    CategoryDO categoryDO = new CategoryDO();
                    categoryDO.setUid(item.getId());
                    categoryDO.setName(item.getName());
                    articleDO.setCategory(categoryDO);
                }
        );

        articleDO.setUid(articleDTO.getId());
        articleDO.setTitle(articleDTO.getTitle());
        articleDO.setContent(articleDTO.getContent());
        articleDO.setHtmlContent(articleDTO.getHtmlContent());
        articleDO.setIsEncrypt(articleDTO.getIsEncrypt());
        articleDO.setSummary(articleDTO.getSummary());

        return articleDO;
    }

    private static ArticleDTO convertToDTO(ArticleDO articleDO) {
        ArticleDTO articleDTO = new ArticleDTO();

        Optional<CategoryDO> categoryDO = Optional.ofNullable(articleDO.getCategory());

        CategoryConverter categoryConverter = new CategoryConverter();

        categoryDO.ifPresent(
                item -> {
                    CategoryDTO category = categoryConverter.convertFromEntity(item);
                    articleDTO.setCategory(category);
                }
        );

        articleDTO.setId(articleDO.getUid());
        articleDTO.setTitle(articleDO.getTitle());
        articleDTO.setContent(articleDO.getContent());
        articleDTO.setHtmlContent(articleDO.getHtmlContent());
        articleDTO.setIsEncrypt(articleDO.getIsEncrypt());
        articleDTO.setSummary(articleDO.getSummary());

        return articleDTO;
    }

}
