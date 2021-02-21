package com.yochalyc.myblog.blog.web.controller.admin;

import com.yochalyc.myblog.blog.core.service.TagService;
import com.yochalyc.myblog.blog.dal.dao.TagDAO;
import com.yochalyc.myblog.blog.dal.model.TagDO;
import com.yochalyc.myblog.blog.util.PageUtil;
import com.yochalyc.myblog.blog.web.model.Result;
import com.yochalyc.myblog.blog.web.model.TagDTO;
import com.yochalyc.myblog.blog.web.model.TagListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/tag")
public class AdminTagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagDAO tagDAO;

    @GetMapping("/list")
    @ResponseBody
    public Result<TagListDTO> getLists(Boolean all, Integer page, Integer pageSize) {
        try {
            page = PageUtil.formatPage(page);

            int count = tagService.count();

            pageSize = all ? count : PageUtil.formatPageSize(pageSize);

            List<TagDO> tagDOList = tagDAO.findAllOrderByName(page, pageSize);

            List<TagDTO> tagDTOList = tagDOList.stream()
                    .map(t -> new TagDTO(t.getUid(), t.getName(), t.getDataCreatedTime(),
                            t.getDataModifiedTime(), t.getArticles().size(), t.getIsUserDeleted()))
                    .collect(Collectors.toList());

            TagListDTO tagListDTO = TagListDTO.builder()
                    .page(page)
                    .pageSize(pageSize)
                    .count(count)
                    .list(tagDTOList)
                    .build();

            return new Result<>(tagListDTO);
        } catch (Exception e) {
            return new Result<>("", e.getMessage());
        }
    }

}
