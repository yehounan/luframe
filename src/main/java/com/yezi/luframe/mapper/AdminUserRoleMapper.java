package com.yezi.luframe.mapper;


import com.yezi.luframe.base.BaseMapper;
import com.yezi.luframe.entity.AdminUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Desc    : 管理员用户角色Mapper
 *
 * @author : yxy
 * @date    : 2018-01-12
 */
@Repository
public interface AdminUserRoleMapper extends BaseMapper<AdminUserRole> {

    /**
     * 根据用户id获取用户角色列表
     * @param userId
     * @return
     */
    List<AdminUserRole> selectByUserId(Long userId);

    /**
     * 根据用户id获取用户角色详细信息
     * @param userId
     * @return
     */
    List<Map> getUserRoleName(@Param("userId") Long userId);


    /**
     * 判断用户是不是超级管理员
     * @param userId
     * @return
     */
   int checkAdmin(@Param("userId") Long userId);
}