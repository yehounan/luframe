package com.yezi.luframe.service;


import com.yezi.luframe.param.AdminOperateLogQueryParam;
import com.yezi.luframe.vo.ExtendVO;

/**
 * @author yezi
 * @date 2018/9/28 10:07
 */
public interface AdminUserOperateLogService {

    /**
     * 查询操作日志列表（分页）
     * @param adminOperateLogQueryParam
     * @return
     */
    ExtendVO getAdminUserOperateLogList(AdminOperateLogQueryParam adminOperateLogQueryParam);
}
