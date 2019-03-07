package com.yezi.luframe.vo;

import lombok.Data;

/**
 * @author yezi
 * @date 2019/3/4 10:59
 */
@Data
public class JsonResult<T> {

    private Integer code;
    private String message;
    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonResult<T> OK() {
        this.code = 0;
        this.message = "success";
        this.data = null;
        return this;
    }

    public JsonResult<T> OK(T t) {
        this.code = 0;
        this.message = "success";
        this.data = t;
        return this;
    }

    public JsonResult<T> isNotLogin() {
        this.code = 1;
        this.message = "没有登录信息";
        this.data = null;
        return this;
    }

    public JsonResult<T> tokenIsExpired() {
        this.code = 2;
        this.message = "token已过期";
        this.data = null;
        return this;
    }

}
