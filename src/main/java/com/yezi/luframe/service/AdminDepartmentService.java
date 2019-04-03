package com.yezi.luframe.service;


import com.yezi.luframe.entity.AdminDepartment;
import com.yezi.luframe.vo.BaseVO;

import java.util.List;

/**
 * @author yxy
 */
public interface AdminDepartmentService {


    /**
     * 分页查询
     *
     * @return
     */
    List<AdminDepartment> listAdminDepartment();

    /**
     * 新增
     *
     * @param deptName
     * @return
     */
    void insertDepartment(String deptName);


    /**
     * 修改
     *
     * @param deptName
     * @param id
     * @return
     */
    void updateDepartment(String deptName, Long id);


    /**
     * 删除
     *
     * @param id
     * @return
     */
    BaseVO deleteDepartment(Long id);


}
