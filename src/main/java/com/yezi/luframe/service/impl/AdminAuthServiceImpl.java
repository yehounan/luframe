package com.yezi.luframe.service.impl;


import com.yezi.luframe.dto.AdminUserPayloadDTO;
import com.yezi.luframe.entity.AdminRole;
import com.yezi.luframe.entity.AdminRoleResource;
import com.yezi.luframe.entity.AdminUser;
import com.yezi.luframe.entity.AdminUserRole;
import com.yezi.luframe.enums.CodeEnum;
import com.yezi.luframe.mapper.*;
import com.yezi.luframe.service.AdminAuthService;
import com.yezi.luframe.util.JwtUtils;
import com.yezi.luframe.util.VOUtils;
import com.yezi.luframe.vo.ExtendVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc    : 管理员用户权限服务实现类
 *
 * @author : yxy
 * @date : 2018-01-14
 */
@Service
@Slf4j
public class AdminAuthServiceImpl implements AdminAuthService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdminUserRoleMapper adminUserRoleMapper;

    @Autowired
    private AdminRoleResourceMapper adminRoleResourceMapper;

    @Autowired
    private AdminResourceMapper adminResourceMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public ExtendVO checkAuthority(String jwt, String url) {
        if (StringUtils.isBlank(jwt)) {
            return VOUtils.returnExtendVOError(CodeEnum.ADMIN_NOT_LOGIN, null);
        }
        ExtendVO extendVO = JwtUtils.unsignWithInfo(jwt, AdminUserPayloadDTO.class);
        if (VOUtils.isOkExtendVO(extendVO)) {
            AdminUserPayloadDTO adminUserPayloadDTO = (AdminUserPayloadDTO) extendVO.getData();
            log.info("adminUserPayloadDTO===" + adminUserPayloadDTO);
            if (adminUserPayloadDTO == null ||
                    adminUserPayloadDTO.getId() == null ||
                    StringUtils.isBlank(adminUserPayloadDTO.getUsername())) {
                return VOUtils.returnExtendVOError(CodeEnum.ERROR_TOKEN, null);
            }
            AdminUser adminUser = adminUserMapper.selectByIdAndUsername(adminUserPayloadDTO.getId(), adminUserPayloadDTO.getUsername());
            log.info("adminUser=" + adminUser);
            if (adminUser == null) {
                return VOUtils.returnExtendVOError(CodeEnum.ADMIN_USER_NOT_EXISTS, null);
            }
            if (adminUser.getState() != 1) {
                return VOUtils.returnExtendVOError(CodeEnum.ADMIN_USER_LOCKED, null);
            }
            log.info("adminUserRoleMapper.selectByUserId(adminUser.getId())");
            List<AdminUserRole> adminUserRoleList = adminUserRoleMapper.selectByUserId(adminUser.getId());
            if (adminUserRoleList == null || adminUserRoleList.size() == 0) {
                return VOUtils.returnExtendVOError(CodeEnum.ADMIN_USER_NOT_ROLE, null);
            }
            List<Long> adminUserRoleIdList = new ArrayList<>();
            for (AdminUserRole adminUserRole : adminUserRoleList) {
                adminUserRoleIdList.add(adminUserRole.getRoleId());
            }
            Example example = new Example(AdminRole.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("id", adminUserRoleIdList);
            List<AdminRole> adminRoleList = adminRoleMapper.selectByExample(example);
            for (AdminRole adminRole : adminRoleList) {
                //超级管理员直接返回成功
                if ("1".equals(adminRole.getIsAdmin())) {
                    return VOUtils.returnExtendVOSuccess(adminUser);
                }
            }
            Example roleResourceExample = new Example(AdminRoleResource.class);
            Example.Criteria roleResourceCriteria = roleResourceExample.createCriteria();
            roleResourceCriteria.andIn("roleId", adminUserRoleIdList);
            log.info("adminRoleResourceMapper.selectByRoleId(adminUserRole.getRoleId())");
            List<AdminRoleResource> adminRoleResourceList = adminRoleResourceMapper.selectByExample(roleResourceExample);
            //如果角色没有被分配资源，则返回失败
            if (adminRoleResourceList == null || adminRoleResourceList.size() == 0) {
                return VOUtils.returnExtendVOError(CodeEnum.ADMIN_USER_NOT_ROLE, null);
            }
            return VOUtils.returnExtendVOSuccess(adminUser);
        }
        return extendVO;
    }
}
