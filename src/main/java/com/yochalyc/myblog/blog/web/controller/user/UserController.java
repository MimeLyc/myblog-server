package com.yochalyc.myblog.blog.web.controller.user;

import com.yochalyc.myblog.blog.core.service.UserService;
import com.yochalyc.myblog.blog.dal.model.UserDO;
import com.yochalyc.myblog.blog.web.model.Result;
import com.yochalyc.myblog.blog.web.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "")
    @ResponseBody
    public Result<Void> addNewUser(@RequestBody UserVO user) {
        UserDO userDO = new UserDO(user.getNick(), user.getEmail(),
                user.getEmail(), user.getPassword());
        try {
            userService.register(userDO);
            return new Result<>(null);
        } catch (Exception e) {
            return new Result<>("500", e.getMessage());
        }
    }
}
