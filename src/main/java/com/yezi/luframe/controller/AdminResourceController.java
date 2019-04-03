package com.yezi.luframe.controller;


import com.yezi.luframe.constant.URIConstants;
import com.yezi.luframe.param.AdminResourceInsertParam;
import com.yezi.luframe.service.AdminResourceService;
import com.yezi.luframe.util.AdminLoginUserUtils;
import com.yezi.luframe.vo.BaseVO;
import com.yezi.luframe.vo.ExtendVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc    : 菜单相关控制器
 * @date    : 2018-01-22
 *
 * @author : yxy
 */
@Slf4j
@RestController
public class AdminResourceController {

    @Autowired
    AdminResourceService adminResourceService;

    @GetMapping(URIConstants.RESOURCE_LIST)
    public ExtendVO getMenuResourceList(@RequestParam(value = "roleId", required = false) Long roleId) {
        return adminResourceService.getResourceList(roleId);
    }

    @GetMapping(URIConstants.MENU_LIST)
    public ExtendVO getMenuList(HttpServletRequest httpServletRequest) {
        Long userId = AdminLoginUserUtils.getAdminUserId(httpServletRequest);
        return adminResourceService.getMenuList(userId);
    }

    @PostMapping(URIConstants.RESOURCE_INSERT)
    public BaseVO resourceInsert(@Validated @RequestBody AdminResourceInsertParam adminResourceInsertParam) {
        return adminResourceService.insertResource(adminResourceInsertParam);
    }

}
