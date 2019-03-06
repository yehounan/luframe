package com.yezi.luframe.config;

import com.yezi.luframe.interceptor.LoginAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Web配置类
 *
 * @author yezi
 * @date 2019/3/4 10:58
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LoginAuthInterceptor getLoginAuthInterceptor() {
        return new LoginAuthInterceptor();
    }

    /**
     * 添加权限验证拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginAuthInterceptor());
    }

}
