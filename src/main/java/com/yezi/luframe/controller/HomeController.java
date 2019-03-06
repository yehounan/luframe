package com.yezi.luframe.controller;

import com.yezi.luframe.annotation.RequireLog;
import com.yezi.luframe.vo.JsonResult;
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
