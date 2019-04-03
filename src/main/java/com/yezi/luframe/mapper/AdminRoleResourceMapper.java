package com.yezi.luframe.mapper;


import com.yezi.luframe.entity.AdminRoleResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Desc    : 角色资源Mapper
 *
 * @author : yxy
 * @date : 2018-01-17
 */
@Repository
public interface AdminRoleResourceMapper extends BaseMapper<AdminRoleResource> {

    /**
     * 根据角色id获取角色资源列表
     *
     * @param roleId
     * @return
     */
    List<AdminRoleResource> selectByRoleId(Long roleId);

    /**
     * 根据角色id列表获取角色资源列表
     *
     * @param roleIdList
     * @return
     */
    List<AdminRoleResource> selectByRoleIdList(List<Long> roleIdList);

}