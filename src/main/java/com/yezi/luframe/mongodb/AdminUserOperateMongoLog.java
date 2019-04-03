package com.yezi.luframe.mongodb;

import lombok.Data;

import javax.persistence.Id;


/**
 * 操作日志
 *
 * @author yezi
 * @date 2019/3/11 14:34
 */
@Data
public class AdminUserOperateMongoLog {


    @Id
    private String id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 请求URI
     */
    private String requestUri;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 执行方法所在类
     */
    private String className;

    /**
     * 执行方法名
     */
    private String methodName;

    /**
     * 执行方法入参
     */
    private String params;

    /**
     * 执行方法返回结果
     */
    private String result;

}
