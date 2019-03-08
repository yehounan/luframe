package com.yezi.luframe.controller;

import com.yezi.luframe.listener.OnlineUserListener;
import com.yezi.luframe.redis.RedisCache;
import com.yezi.luframe.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author yezi
 * @date 2019/3/4 10:58
 */
@Slf4j
@Controller
public class HomeController {

    @Autowired
    RedisCache redisCache;

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
    public String onlineUsers(String location, HttpServletRequest request) {
        log.info("location{}", location);
        HttpSession session = request.getSession(true);
        session.setAttribute("hello", "hello");
        return "在线用户数:【" + OnlineUserListener.onlineUsers + "】";
    }


    @GetMapping(value = "/hello/redis/put/{redisValue}")
    @ResponseBody
    public JsonResult testRedisPut(@PathVariable("redisValue") String redisValue) {
        redisCache.setCacheValue("DEMO_REDIS_KEY", redisValue, 6000);
        return new JsonResult().OK();
    }

    @GetMapping(value = "/hello/redis/get/{redisKey}")
    @ResponseBody
    public JsonResult testRedisGet(@PathVariable("redisKey") String redisKey) {
        return new JsonResult().OK(redisCache.getCacheValue(redisKey, null));
    }


}
