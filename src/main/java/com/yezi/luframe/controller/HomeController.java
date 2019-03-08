package com.yezi.luframe.controller;

import com.yezi.luframe.listener.OnlineUserListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    /**
     * session对象创建需要显示的调用getSession()方法
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/hello/onlineUsers")
    @ResponseBody
    public String onlineUsers(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("hello", "hello");
        return "在线用户数:【" + OnlineUserListener.onlineUsers + "】";
    }


}
