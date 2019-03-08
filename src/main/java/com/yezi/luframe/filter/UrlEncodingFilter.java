package com.yezi.luframe.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 过滤器：Filter是Servlet技术中最实用的技术，Web开发人员通过Filter技术，
 * 对web服务器管理的所有web资源：例如Jsp, Servlet, 静态图片文件或静态 html 文件等进行拦截，
 * 从而实现一些特殊的功能。例如实现URL级别的权限访问控制、过滤敏感词汇、压缩响应信息等一些高级功能。
 * 它主要用于对用户请求进行预处理，也可以对HttpServletResponse进行后处理。使用Filter的完整流程：Filter对用户请求进行预处理，
 * 接着将请求交给Servlet进行处理并生成响应，最后Filter再对服务器响应进行后处理。
 *
 * @author yezi
 * @date 2019/3/8 11:07
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class UrlEncodingFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器创建...");
    }

    @Override
    public void destroy() {
        log.info("过滤器销毁...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("请求URI{}", request.getRequestURI());
        filterChain.doFilter(request, response);
    }
}
