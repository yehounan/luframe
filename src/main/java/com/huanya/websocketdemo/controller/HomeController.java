package com.huanya.websocketdemo.controller;

import com.huanya.websocketdemo.annotation.RequireLog;
import com.huanya.websocketdemo.dao.UserDao;
import com.huanya.websocketdemo.entity.User;
import com.huanya.websocketdemo.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author yezi
 * @date 2019/3/4 10:58
 */
@RestController
public class HomeController {

    @Autowired
    UserDao userDao;

    @RequireLog
    @GetMapping(value = "/hello")
    public JsonResult hello() {
        return new JsonResult().OK();
    }

    @RequireLog
    @PostMapping(value = "/user/save")
    public JsonResult save(@Valid @RequestBody User user) {
        user.setCreateTime(new Date());
        JsonResult jsonResult = new JsonResult(0, "ok", userDao.save(user));
        return jsonResult;
    }
}
