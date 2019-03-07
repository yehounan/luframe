package com.yezi.luframe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yezi
 * @date 2019/3/4 10:58
 */
@Controller
public class HomeController {

    /**
     * demo首页
     *
     * @return
     */
    @GetMapping(value = "/hello")
    public String hello() {
        return "index";
    }


}
