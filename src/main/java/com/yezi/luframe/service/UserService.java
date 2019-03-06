package com.yezi.luframe.service;

import com.yezi.luframe.base.PageInfoData;
import com.yezi.luframe.entity.User;
import com.yezi.luframe.param.UserSearchParam;

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
