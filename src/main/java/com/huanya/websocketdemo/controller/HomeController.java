package com.huanya.websocketdemo.controller;

import com.huanya.websocketdemo.annotation.RequireLog;
import com.huanya.websocketdemo.vo.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yezi
 * @date 2019/3/4 10:58
 */
@RestController
public class HomeController {

    @RequireLog
    @GetMapping(value = "/hello")
    public JsonResult hello() {
        return new JsonResult().OK();
    }


}
