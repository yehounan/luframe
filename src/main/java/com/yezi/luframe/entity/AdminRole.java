package com.yezi.luframe.entity;

import com.yezi.luframe.base.BaseDomain;
import lombok.Data;

/**
 * Description 角色表数据库对象
 *
 * @author yxy
 * @date 2018/12/12
 */
@Data
public class AdminRole extends BaseDomain {
    private String roleName;

    private String remark;

    /**
     * 是否是admin 0：不是 ， 1是
     */
    private String isAdmin;

}