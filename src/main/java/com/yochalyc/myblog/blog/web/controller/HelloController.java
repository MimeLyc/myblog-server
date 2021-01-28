package com.yochalyc.myblog.blog.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String test() {
        return "Greetings from Spring Boot!";
    }
}
