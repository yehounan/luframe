package com.yezi.luframe.service;


import com.yezi.luframe.base.PageParam;
import com.yezi.luframe.dto.AdminUserDTO;
import com.yezi.luframe.param.AdminUserInsertParam;
import com.yezi.luframe.param.AdminUserUpdateParam;
import com.yezi.luframe.vo.BaseVO;
import com.yezi.luframe.vo.ExtendVO;

import java.util.List;

/**
 * Desc    : 管理员用户Service
 * @author  : yxy
 * @date    : 2018-01-17
 */
public interface AdminUserService {

    /**
     * 获取管理员用户列表
     * @param pageParam
     * @return
     */
    ExtendVO getAdminUserList(PageParam pageParam);

    /**
     * 根据id列表获取管理员用户
     * @param ids
     * @return
     */
    ExtendVO getAdminUserByIds(List<Long> ids);

    /**
     * 根据用户名称获取管理员用户
     * @param name
     * @return
     */
    ExtendVO getAdminUserIdsByName(String name);

    /**
     * 获取所有管理员用户
     *
     * @return
     */
    ExtendVO getAdminUsernameAll();

    /**
     * 新增用户
     *
     * @param adminUserInsertParam
     * @return
     */
    BaseVO adminUserInsert(AdminUserInsertParam adminUserInsertParam);

    /**
     * 更新用户
     * @param adminUserUpdateParam
     * @return
     */
    BaseVO adminUserUpdate(AdminUserUpdateParam adminUserUpdateParam);

    /**
     * 删除用户
     * @param id
     * @return
     */
    BaseVO adminUserDelete(Long id);

    /**
     * 重置密码
     * @param id
     * @param pwd
     * @return
     */
    BaseVO adminUserResetPassword(Long id, String pwd);


    /**
     * 根据用户id获取用户信息
     *
     * @param userId
     * @return
     */
    AdminUserDTO getAdminUser(Long userId);

}
