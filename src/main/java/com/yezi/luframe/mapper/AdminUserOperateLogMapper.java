package com.yezi.luframe.mapper;


import com.yezi.luframe.dto.AdminUserOperateLogDTO;
import com.yezi.luframe.entity.AdminUserOperateLog;
import com.yezi.luframe.param.AdminOperateLogQueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Desc    : 用户操作日志Mapper
 * @author  : yxy
 * @date    : 2018-05-16
 */
@Repository
public interface AdminUserOperateLogMapper extends BaseMapper<AdminUserOperateLog> {

    /**
     * 获取用户管理员操作日志列表
     * @param adminOperateLogQueryParam
     * @return
     */
    List<AdminUserOperateLogDTO> getAdminUserOperateLogList(AdminOperateLogQueryParam adminOperateLogQueryParam);

}
