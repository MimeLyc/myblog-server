package com.yochalyc.myblog.blog.web.controller.admin;

import com.yochalyc.myblog.blog.core.service.AdminService;
import com.yochalyc.myblog.blog.web.model.LoginResultVO;
import com.yochalyc.myblog.blog.web.model.Result;
import com.yochalyc.myblog.blog.web.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //    login response
    @PostMapping(path = "/login")
    @ResponseBody
    public Result<LoginResultVO> login(String username, String password) {
        try {

            adminService.login(username, password);
        } catch (NullPointerException e) {
            return new Result<>("403", "用户名或密码错误");
        }

        long expireInterval = 7 * 24 * 60 * 60 * 1000;
        Date tokenExpiresIn = new Date();
        tokenExpiresIn.setTime(tokenExpiresIn.getTime() + expireInterval);

        Token token = new Token("test", tokenExpiresIn, expireInterval);
        LoginResultVO loginResultVO = new LoginResultVO("111", token, new Date(), username);

        return new Result<>(loginResultVO);
    }

}
