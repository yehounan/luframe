package com.yezi.luframe.service;


import com.yezi.luframe.param.AdminResourceInsertParam;
import com.yezi.luframe.vo.BaseVO;
import com.yezi.luframe.vo.ExtendVO;

/**
 * Desc    : 资源Service
 * @date    : 2018-01-22
 *
 * @author : yxy
 */
public interface AdminResourceService {

    /**
     * 根据角色ID获取资源列表
     * @param roleId
     * @return
     */
    ExtendVO getResourceList(Long roleId);

    /**
     * 获取菜单列表
     * @param userId
     * @return
     */
    ExtendVO getMenuList(Long userId);

    /**
     * 新增资源
     * @param adminResourceInsertParam
     * @return
     */
    BaseVO insertResource(AdminResourceInsertParam adminResourceInsertParam);


}
