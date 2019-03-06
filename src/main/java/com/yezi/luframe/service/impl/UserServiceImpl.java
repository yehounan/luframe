package com.yezi.luframe.service.impl;

import com.yezi.luframe.base.PageInfoData;
import com.yezi.luframe.dao.UserDao;
import com.yezi.luframe.entity.User;
import com.yezi.luframe.param.UserSearchParam;
import com.yezi.luframe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author yezi
 * @date 2019/3/6 10:59
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    /**
     * 用户列表查询（分页）
     *
     * @param searchParam
     * @return
     */
    @Override
    public PageInfoData<User> listUser(UserSearchParam searchParam) {
        Pageable pageable = PageRequest.of(searchParam.getPageNum(), searchParam.getPageSize(), Sort.Direction.DESC, "id");
        User user = new User();
        if (!StringUtils.isEmpty(searchParam.getName())) {
            user.setName(searchParam.getName());
        }
        if (!StringUtils.isEmpty(searchParam.getAddress())) {
            user.setAddress(searchParam.getAddress());
        }
        Example<User> example = Example.of(user);
        Page<User> page = userDao.findAll(example, pageable);
        return new PageInfoData<>(page);
    }
}
