package com.yezi.luframe.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 需求日志注解
 *
 * @author yezi
 * @date 2019/3/4 11:08
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface RequireLog {

    String value() default "";
}
