package com.yezi.luframe.vo;

import lombok.Data;

/**
 * 参数校验异常结果
 *
 * @author yezi
 * @date 2019/3/4 10:59
 */
@Data
public class ArgumentInvalidResult {

    private String field;
    private Object rejectedValue;
    private String defaultMessage;

}
