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
 * 拦截器：Interceptor 在AOP（Aspect-Oriented Programming）中用于在某个方法或字段被访问之前，进行拦截然后在之前或之后加入某些操作。
 * 比如日志，安全等。一般拦截器方法都是通过动态代理的方式实现。
 * 可以通过它来进行权限验证，或者判断用户是否登陆，或者是像12306 判断当前时间是否是购票时间。
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
        if (request.getServletPath().contains(login) || request.getServletPath().contains(hello)) {
            return true;
        }
        String jwt = request.getHeader("Authentication");
        if (!StringUtils.isEmpty(jwt)) {
            User user = JwtUtils.unsign(jwt, User.class);
            if (null != user) {
                log.info("登录用户信息{}", user);
                return true;
            }
            log.info("token已过期....");
            JsonUtils.returnJson(response, new JsonResult<>().tokenIsExpired());
            return false;
        }
        JsonUtils.returnJson(response, new JsonResult<>().isNotLogin());
        return false;
    }


}
