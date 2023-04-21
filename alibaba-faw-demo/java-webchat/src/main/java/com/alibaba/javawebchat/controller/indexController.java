package com.alibaba.javawebchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author : PeiHang
 * @create 2023/4/21 14:50
 */
@Controller
public class indexController {

    @GetMapping("/")
    public String index()
    {
        return "chat";
    }
}
