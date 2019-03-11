package com.yezi.luframe.service;

import com.yezi.luframe.param.LoginParam;
import com.yezi.luframe.vo.JsonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yezi
 * @date 2019/3/6 15:21
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param loginParam
     * @return
     */
    JsonResult login(LoginParam loginParam, HttpServletRequest request);
}
