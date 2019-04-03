package com.yezi.luframe.service;


import com.yezi.luframe.vo.ExtendVO;

/**
 * Desc    : 管理员用户权限服务
 * @date    : 2018-01-14
 *
 * @author : yxy
 */
public interface AdminAuthService {

    /**
     * jwt是否拥有对url操作的权限的判断
     *
     * @param jwt
     * @param url
     * @return
     */
    ExtendVO checkAuthority(String jwt, String url);

}
