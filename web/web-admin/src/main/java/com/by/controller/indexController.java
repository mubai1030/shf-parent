package com.by.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiaobai
 * @create 2023-03-26 12:24
 */
@Controller
public class indexController {
    //去首页
    @RequestMapping("/")
    public String index(){
        return "frame/index";
    }

    //去主页面
    @RequestMapping("/main")
    public String main(){
        return "frame/main";
    }

}
