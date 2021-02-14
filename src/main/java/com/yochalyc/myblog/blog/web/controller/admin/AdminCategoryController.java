package com.yochalyc.myblog.blog.web.controller.admin;

import com.yochalyc.myblog.blog.core.service.CategoryService;
import com.yochalyc.myblog.blog.web.model.CategoryDTO;
import com.yochalyc.myblog.blog.web.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

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

}
