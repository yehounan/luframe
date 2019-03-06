package com.yezi.luframe.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yezi
 * @date 2019/3/6 15:18
 */
@Data
public class LoginParam {

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;

    /**
     * 用户密码
     */
    @NotNull(message = "用户密码不能为空")
    private String password;

    /**
     * 图片验证码
     */
    private String imgCode;

    /**
     * 图片验证码id
     */
    private String imgCodeId;
}
