package com.yochalyc.myblog.blog.core.converter;

import com.yochalyc.myblog.blog.dal.model.CommentDO;
import com.yochalyc.myblog.blog.web.model.CommentDTO;

public class CommentConverter extends Converter<CommentDTO, CommentDO> {

    public CommentConverter() {
        super(CommentConverter::convertToEntity, CommentConverter::convertToDTO);
    }

    private static CommentDO convertToEntity(CommentDTO commentDTO) {
        CommentDO commentDO = new CommentDO();

        commentDO.setUid(commentDTO.getId());
        commentDO.setName(commentDTO.getName());
        commentDO.setContentJson(commentDTO.getContent());
        commentDO.setDataCreatedTime(commentDTO.getCreateTime());
        commentDO.setIsAuthor(commentDTO.getIsAuthor());

        return commentDO;
    }

    private static CommentDTO convertToDTO(CommentDO commentDO) {
        return new CommentDTO(commentDO.getUid(), commentDO.getName(),
                commentDO.getArticle().getTitle(), commentDO.getContentJson(),
                commentDO.getIsAuthor(), commentDO.getParentComment().getUid(),
                commentDO.getReplyComment().getUid(), commentDO.getDataCreatedTime(),
                commentDO.getIsDeleted());
    }

}
