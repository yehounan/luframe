package com.yezi.luframe.service.impl;

import com.yezi.luframe.entity.AdminDepartment;
import com.yezi.luframe.entity.AdminUser;
import com.yezi.luframe.enums.CodeEnum;
import com.yezi.luframe.mapper.AdminDepartmentMapper;
import com.yezi.luframe.mapper.AdminUserMapper;
import com.yezi.luframe.service.AdminDepartmentService;
import com.yezi.luframe.util.VOUtils;
import com.yezi.luframe.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 部门管理
 *
 * @author humb
 */
@Service
public class AdminDepartmentImpl implements AdminDepartmentService {

    @Autowired
    private AdminDepartmentMapper adminDepartmentMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public List<AdminDepartment> listAdminDepartment() {
        List<AdminDepartment> list = adminDepartmentMapper.selectAll();
        return list;
    }

    /**
     * 新增
     *
     * @return
     */
    @Override
    public void insertDepartment(String deptName) {
        AdminDepartment dept = new AdminDepartment();
        dept.setCreateTime(new Date());
        dept.setDeptName(deptName);
        adminDepartmentMapper.insert(dept);
    }

    /**
     * 修改
     *
     * @return
     */
    @Override
    public void updateDepartment(String deptName, Long id) {
        AdminDepartment dept = new AdminDepartment();
        dept.setId(id);
        dept.setDeptName(deptName);
        adminDepartmentMapper.updateByPrimaryKeySelective(dept);
    }


    /**
     * 删除
     *
     * @return
     */
    @Override
    public BaseVO deleteDepartment(Long id) {
        AdminUser user = new AdminUser();
        user.setDeptId(Integer.parseInt(id.toString()));
        List<AdminUser> listUser = adminUserMapper.select(user);
        if (listUser.size() > 0) {
            return VOUtils.returnBaseVODynamic(CodeEnum.ERROR, "部门下面还存在人员，不能删除");
        }
        adminDepartmentMapper.deleteByPrimaryKey(id);
        return VOUtils.returnBaseVOSuccess();
    }


}
