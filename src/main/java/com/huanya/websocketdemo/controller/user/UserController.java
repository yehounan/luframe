package com.huanya.websocketdemo.controller.user;

import com.huanya.websocketdemo.annotation.RequireLog;
import com.huanya.websocketdemo.base.PageInfoData;
import com.huanya.websocketdemo.entity.User;
import com.huanya.websocketdemo.param.UserParam;
import com.huanya.websocketdemo.param.UserSearchParam;
import com.huanya.websocketdemo.service.UserService;
import com.huanya.websocketdemo.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author yezi
 * @date 2019/3/6 11:40
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 保存用户（新增或者修改）
     *
     * @param userParam
     * @return
     */
    @RequireLog
    @PostMapping(value = "/user/save")
    public JsonResult save(@Valid @RequestBody UserParam userParam) {
        userParam.setCreateTime(new Date());
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user = userService.save(user);
        return new JsonResult<>().OK(user);
    }


    /**
     * 用户列表查询（分页）
     *
     * @param searchParam
     * @return
     */
    @PostMapping(value = "/user/search")
    public JsonResult listUser(@Valid @RequestBody UserSearchParam searchParam) {
        log.info("UserSearchParam ={}", searchParam);
        PageInfoData<User> pageInfoData = userService.listUser(searchParam);
        return new JsonResult<>().OK(pageInfoData);
    }
}
