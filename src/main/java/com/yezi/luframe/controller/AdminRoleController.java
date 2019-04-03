package com.yezi.luframe.controller;


import com.yezi.luframe.constant.URIConstants;
import com.yezi.luframe.entity.AdminRole;
import com.yezi.luframe.enums.CodeEnum;
import com.yezi.luframe.param.AdminRoleUpdateParam;
import com.yezi.luframe.service.AdminRoleService;
import com.yezi.luframe.util.VOUtils;
import com.yezi.luframe.vo.BaseVO;
import com.yezi.luframe.vo.ExtendVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Desc    : 角色相关控制器
 * @date    : 2018-01-22
 *
 * @author : yxy
 */
@Slf4j
@RestController
public class AdminRoleController {

    @Autowired
    AdminRoleService adminRoleService;


    @PostMapping(URIConstants.ROLE_INSERT)
    public BaseVO roleInsert(@RequestBody AdminRole adminRole) {
        try {
            String roleName = adminRole.getRoleName();
            String remark = adminRole.getRemark();
            return adminRoleService.roleInsert(roleName, remark);
        } catch (Exception e) {
            log.error("",e);
            return VOUtils.returnBaseVOError(CodeEnum.ERROR);
        }
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @GetMapping("role/delete")
    public BaseVO roleDelete(@RequestParam("id") Long id) {
        try {
            return adminRoleService.roleDelete(id);
        } catch (Exception e) {
            log.error("",e);
            return VOUtils.returnBaseVOError(CodeEnum.ERROR);
        }
    }

    /**
     * 修改角色
     *
     * @param adminRole{id,remark,roleName}
     * @return
     */
    @PostMapping("role/update")
    public BaseVO roleUpdate(@RequestBody AdminRole adminRole) {
        try {
            Long id = adminRole.getId();
            String remark = adminRole.getRemark();
            String roleName = adminRole.getRoleName();
            return adminRoleService.roleUpdate(id, remark, roleName);
        } catch (Exception e) {
            log.error("",e);
            return VOUtils.returnBaseVOError(CodeEnum.ERROR);
        }
    }

    /**
     * 给角色分配权限
     *
     * @param adminRoleUpdateParam
     * @return
     */
    @PostMapping(URIConstants.ROLE_UPDATE_AUTHORITY)
    public BaseVO roleUpdateAuthority(@Validated @RequestBody AdminRoleUpdateParam adminRoleUpdateParam) {
        return adminRoleService.roleUpdateAuthority(adminRoleUpdateParam);
    }

    /**
     * 获取角色列表 不分页
     *
     * @return
     */
    @GetMapping(URIConstants.ROLE_LIST)
    public ExtendVO adminRoleList() {
        return adminRoleService.getAdminRoleList();
    }

}
