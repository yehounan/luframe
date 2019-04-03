package com.yezi.luframe.service;



import com.yezi.luframe.param.AdminLoginParam;
import com.yezi.luframe.param.AdminOperateLogQueryParam;
import com.yezi.luframe.vo.ExtendVO;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc    : 登录Service
 * @date    : 2018-01-12
 *
 * @author : yxy
 */
public interface AdminLoginService {

    /**
     * 检查管理员用户密码：
     * 正确：生成token
     * 错误：给出错误信息
     * @param adminLoginParam
     * @param httpServletRequest
     * @return
     */
    ExtendVO getTokenByAdminUserPassword(AdminLoginParam adminLoginParam, HttpServletRequest httpServletRequest);

    /**
     * 后台管理操作日志查询
     * @param adminOperateLogQueryParam
     * @return
     */
    ExtendVO operateLogQuery(AdminOperateLogQueryParam adminOperateLogQueryParam);

    /**
     * 查询当前登录管理员用户信息
     * @param httpServletRequest
     * @return
     */
    ExtendVO getSelfInfo(HttpServletRequest httpServletRequest);
}
