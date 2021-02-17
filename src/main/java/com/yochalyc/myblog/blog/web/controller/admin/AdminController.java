package com.yochalyc.myblog.blog.web.controller.admin;

import com.yochalyc.myblog.blog.core.service.*;
import com.yochalyc.myblog.blog.dal.model.AdminDO;
import com.yochalyc.myblog.blog.web.model.AdminStatisticInfo;
import com.yochalyc.myblog.blog.web.model.LoginResultDTO;
import com.yochalyc.myblog.blog.web.model.Result;
import com.yochalyc.myblog.blog.web.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.yochalyc.myblog.blog.config.GlobalProperties.ADMIN_TOKEN_DURATION;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @PostMapping(path = "/login")
    @ResponseBody
    public Result<LoginResultDTO> login(String username, String password) {
        try {
            adminService.login(username, password);
        } catch (NullPointerException e) {
            return new Result<>("403", "用户名或密码错误");
        }

        AdminDO adminDO = adminService.login(username, password);

        LoginResultDTO loginResultDTO = LoginResultDTO
                .builder()
                .lastLoginTime(adminDO.getLastLoginTime())
                .token(new Token(adminDO.getAccessToken(),
                        adminDO.getTokenExpireIn(), ADMIN_TOKEN_DURATION))
                .userName(adminDO.getAdminName())
                .build();

        return new Result<>(loginResultDTO);
    }

    @PostMapping(path = "")
    @ResponseBody
    public Result<Void> register(String adminName, String password) {
        try {
            adminService.register(adminName, password);
            return new Result<>(null);
        } catch (Exception e) {
            return new Result<>("", e.getMessage());
        }
    }

    @GetMapping(path = "/statistic")
    @ResponseBody
    public Result<AdminStatisticInfo> getStatistic() {
        return null;
    }

}
