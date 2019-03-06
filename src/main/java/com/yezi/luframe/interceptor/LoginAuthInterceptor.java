package com.yezi.luframe.interceptor;

import com.yezi.luframe.entity.User;
import com.yezi.luframe.util.JsonUtils;
import com.yezi.luframe.util.JwtUtils;
import com.yezi.luframe.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author yezi
 * @date 2019/3/6 14:54
 */
@Slf4j
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String login = "/login";
        String hello = "/hello";
        if (login.equals(request.getServletPath()) || hello.equals(request.getServletPath())) {
            return true;
        }
        String jwt = request.getHeader("Authentication");
        if (!StringUtils.isEmpty(jwt)) {
            User user = JwtUtils.unsign(jwt, User.class);
            if (null != user) {
                return true;
            }
            return false;
        }
        JsonUtils.returnJson(response, new JsonResult<>().isNotLogin());
        return false;
    }


}
