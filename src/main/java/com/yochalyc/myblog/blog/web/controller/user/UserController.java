package com.yochalyc.myblog.blog.web.controller.user;

import com.yochalyc.myblog.blog.dal.dao.UserDAO;
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
    private UserDAO userDAO;

    @PostMapping(path = "")
    public @ResponseBody String addNewUser(@RequestBody )
}
