package com.yezi.luframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author yezi
 * @date 2019/2/25 10:43
 */
@SpringBootApplication
@ComponentScan("com.yezi.luframe.*")
@MapperScan(basePackages = {"com.yezi.luframe.mapper"})
@ServletComponentScan(basePackages = "com.yezi.luframe.*")
public class LuFrameApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuFrameApplication.class, args);
    }

}
