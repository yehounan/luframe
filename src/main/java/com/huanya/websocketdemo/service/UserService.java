package com.huanya.websocketdemo.service;

import com.huanya.websocketdemo.base.PageInfoData;
import com.huanya.websocketdemo.entity.User;
import com.huanya.websocketdemo.param.UserSearchParam;

/**
 * @author yezi
 * @date 2019/3/6 10:58
 */
public interface UserService {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    User save(User user);

    /**
     * 用户列表查询（分页）
     *
     * @param searchParam
     * @return
     */
    PageInfoData<User> listUser(UserSearchParam searchParam);
}
