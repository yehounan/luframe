package com.yezi.luframe.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author yezi
 * @date 2019/3/8 10:22
 */
@Slf4j
@WebListener
public class OnlineUserListener implements HttpSessionListener {

    public static int onlineUsers = 0;

    /**
     * HttpSession创建时触发监听器
     *
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlineUsers++;
        log.info("在线用户+1，当前在线用户数:{}", onlineUsers);
    }

    /**
     * HttpSession销毁时触发监听器
     *
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        onlineUsers--;
        log.info("在线用户-1，当前在线用户数:{}", onlineUsers);
    }
}
