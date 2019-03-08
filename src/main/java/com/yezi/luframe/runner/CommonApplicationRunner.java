package com.yezi.luframe.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 系统启动完成执行类
 *
 * @author yezi
 * @date 2019/3/8 11:39
 */
@Slf4j
@Order(1)
@Component
public class CommonApplicationRunner implements ApplicationRunner {

    /**
     * 用于系统启动执行完成之后执行，通常用于加载一些常用配置文件或者清除缓存数据
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("通用启动执行类已启动...");
    }
}
