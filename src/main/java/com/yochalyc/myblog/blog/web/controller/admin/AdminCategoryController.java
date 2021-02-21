package com.yochalyc.myblog.blog.web.controller.admin;

import com.yochalyc.myblog.blog.core.service.CategoryService;
import com.yochalyc.myblog.blog.dal.dao.CategoryDAO;
import com.yochalyc.myblog.blog.dal.model.CategoryDO;
import com.yochalyc.myblog.blog.util.PageUtil;
import com.yochalyc.myblog.blog.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryDAO categoryDAO;

    @PostMapping("")
    @ResponseBody
    public Result<String> add(@RequestBody CategoryDTO request) {
        try {
            String categoryId = categoryService.add(request.getName(), request.getCanDelete());
            return new Result<>(categoryId);
        } catch (Exception e) {
            return new Result<>("", e.getMessage());
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public Result<CategoryListDTO> getLists(Boolean all, Integer page, Integer pageSize) {
        try {
            page = PageUtil.formatPage(page);

            int count = categoryService.count();

            pageSize = all ? count : PageUtil.formatPageSize(pageSize);

            List<CategoryDO> categoryDOList = categoryDAO.findAllOrderByName(page, pageSize);

            List<CategoryDTO> tagDTOList = categoryDOList.stream()
                    .map(t -> new CategoryDTO(t.getUid(), t.getName(), t.getCanDelete(),
                            t.getDataCreatedTime(), t.getDataModifiedTime(),
                            t.getArticles().size(), t.getIsUserDeleted()))
                    .collect(Collectors.toList());

            CategoryListDTO categoryListDTO = CategoryListDTO.builder()
                    .page(page)
                    .pageSize(pageSize)
                    .count(count)
                    .list(tagDTOList)
                    .build();

            return new Result<>(categoryListDTO);
        } catch (Exception e) {
            return new Result<>("", e.getMessage());
        }
    }
}
