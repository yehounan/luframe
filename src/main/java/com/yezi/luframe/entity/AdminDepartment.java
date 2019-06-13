package com.yezi.luframe.entity;


import com.yezi.luframe.base.BaseDomain;
import lombok.Data;

/**
 * Description 部门表数据库对象
 *
 * @author yxy
 * @date 2018/12/12
 */
@Data
public class AdminDepartment extends BaseDomain {
    private String deptName;

}