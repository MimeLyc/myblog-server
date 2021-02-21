package com.yochalyc.myblog.blog.web.controller.admin;

import com.yochalyc.myblog.blog.core.converter.CommentConverter;
import com.yochalyc.myblog.blog.core.service.CommentService;
import com.yochalyc.myblog.blog.dal.dao.CommentDAO;
import com.yochalyc.myblog.blog.dal.model.CommentDO;
import com.yochalyc.myblog.blog.util.PageUtil;
import com.yochalyc.myblog.blog.web.model.CommentListDTO;
import com.yochalyc.myblog.blog.web.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/comment")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentDAO commentDAO;

    @GetMapping("/alllist")
    @ResponseBody
    public Result<CommentListDTO> getLists(Integer page, Integer pageSize) {
        try {
            page = PageUtil.formatPage(page);

            int count = commentService.count();

            pageSize = PageUtil.formatPageSize(pageSize);

            List<CommentDO> commentDOList = commentDAO.findAllOrderByCreateTime(page, pageSize);

            CommentConverter converter = new CommentConverter();
            CommentListDTO commentListDTO = CommentListDTO.builder()
                    .page(page)
                    .pageSize(pageSize)
                    .count(count)
                    .list(converter.createFromEntities(commentDOList))
                    .build();

            return new Result<>(commentListDTO);
        } catch (Exception e) {
            return new Result<>("", e.getMessage());
        }
    }

}
