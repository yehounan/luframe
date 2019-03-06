package com.huanya.websocketdemo.param;

import com.huanya.websocketdemo.base.TimeScopeNotRequiredPageParam;
import lombok.Data;

/**
 * 用户列表查询条件（分页）
 *
 * @author yezi
 * @date 2019/3/6 11:39
 */
@Data
public class UserSearchParam extends TimeScopeNotRequiredPageParam {

    /**
     * 姓名
     */
    private String name;

    /**
     * 地址
     */
    private String address;
}
