package com.yezi.luframe.controller;


import com.yezi.luframe.base.PageParam;
import com.yezi.luframe.constant.URIConstants;
import com.yezi.luframe.param.AdminUserInsertParam;
import com.yezi.luframe.param.AdminUserUpdateParam;
import com.yezi.luframe.service.AdminUserService;
import com.yezi.luframe.vo.BaseVO;
import com.yezi.luframe.vo.ExtendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 用户管理
 *
 * @author humb
 */
@RestController
public class AdminSysUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping(URIConstants.ADMINUSER_LIST)
    public ExtendVO adminUserList(@RequestBody PageParam pageParam) {
        return adminUserService.getAdminUserList(pageParam);
    }

    @PostMapping(URIConstants.ADMINUSER_INSERT)
    public BaseVO adminUserInsert(@RequestBody AdminUserInsertParam adminUserInsertParam) {
        return adminUserService.adminUserInsert(adminUserInsertParam);
    }

    @PostMapping(URIConstants.ADMINUSER_UPDATE)
    public BaseVO adminUserUpdate(@Validated @RequestBody AdminUserUpdateParam adminUserUpdateParam) {
        return adminUserService.adminUserUpdate(adminUserUpdateParam);
    }

    @GetMapping(URIConstants.ADMINUSER_DELETE)
    public BaseVO adminUserDelete(@RequestParam("id") Long id) {
        return adminUserService.adminUserDelete(id);
    }

    @GetMapping(URIConstants.ADMINUSER_RESET_PASSWORD)
    public BaseVO adminUserResetPassword(@RequestParam("id") Long id, @RequestParam("pwd") String pwd) {
        return adminUserService.adminUserResetPassword(id, pwd);
    }


}
