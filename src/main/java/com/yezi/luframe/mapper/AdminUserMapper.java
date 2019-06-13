package com.yezi.luframe.mapper;


import com.yezi.luframe.base.BaseMapper;
import com.yezi.luframe.dto.AdminUserDTO;
import com.yezi.luframe.entity.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Desc    : 管理员用户Mapper
 *
 * @author : yxy
 * @date : 2018-01-17
 */
@Repository
public interface AdminUserMapper extends BaseMapper<AdminUser> {

    /**
     * 根据用户名称获取管理员用户
     *
     * @param username
     * @return
     */
    AdminUser selectByUsername(String username);

    /**
     * 根据用户id和用户名获取管理员用户
     *
     * @param id
     * @param username
     * @return
     */
    AdminUser selectByIdAndUsername(@Param("id") Long id, @Param("username") String username);

    /**
     * 获取所有管理员用户列表
     *
     * @return
     */
    List<AdminUserDTO> selectUserList();

    /**
     * 根据用户id获取用户信息
     *
     * @param userId
     * @return
     */
    AdminUserDTO getAdminUser(@Param("userId") Long userId);
}