package com.yezi.luframe.controller;

import com.yezi.luframe.annotation.RequireLog;
import com.yezi.luframe.param.LoginParam;
import com.yezi.luframe.service.LoginService;
import com.yezi.luframe.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 登录控制器
 *
 * @author yezi
 * @date 2019/3/6 15:17
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 登录
     *
     * @param loginParam
     * @return
     */
    @RequireLog
    @PostMapping(value = "/login")
    public JsonResult login(@Valid @RequestBody LoginParam loginParam, HttpServletRequest request) {
        return loginService.login(loginParam, request);
    }
}
