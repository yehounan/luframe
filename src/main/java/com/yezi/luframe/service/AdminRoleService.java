package com.yezi.luframe.service;


import com.yezi.luframe.param.AdminRoleUpdateParam;
import com.yezi.luframe.vo.BaseVO;
import com.yezi.luframe.vo.ExtendVO;

/**
 * Desc    : 角色Service
 * @author  : yxy
 * @date    : 2018-03-15
 */
public interface AdminRoleService {

    /**
     * 新增角色
     * @param roleName
     * @param remark
     * @return
     */
    BaseVO roleInsert(String roleName, String remark);


    /**
     * 删除角色
     * @param id
     * @return
     */
    BaseVO roleDelete(Long id);

    /**
     * 修改角色
     * @param id
     * @param remark
     * @param roleName
     * @return
     */
    BaseVO roleUpdate(Long id, String remark, String roleName) ;

    /**
     * 角色权限更新
     * @param adminRoleUpdateParam
     * @return
     */
    BaseVO roleUpdateAuthority(AdminRoleUpdateParam adminRoleUpdateParam);


    /**
     * 角色列表不分页
     * @return
     */
    ExtendVO getAdminRoleList();
}
