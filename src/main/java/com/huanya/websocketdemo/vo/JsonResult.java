package com.huanya.websocketdemo.vo;

import lombok.Data;

/**
 * @author yezi
 * @date 2019/3/4 10:59
 */
@Data
public class JsonResult {

    private Integer code;
    private String message;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonResult OK() {
        this.code = 0;
        this.message = "the response is ok!";
        this.data = null;
        return this;
    }
}
