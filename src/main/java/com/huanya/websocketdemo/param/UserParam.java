package com.huanya.websocketdemo.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author yezi
 * @date 2019/3/6 11:01
 */
@Data
public class UserParam {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    private String name;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    private Integer age;

    /**
     * 地址
     */
    @NotNull(message = "地址不能为空")
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;
}
