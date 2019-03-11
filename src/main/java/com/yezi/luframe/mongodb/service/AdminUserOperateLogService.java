package com.yezi.luframe.mongodb.service;

import com.yezi.luframe.base.PageInfoData;
import com.yezi.luframe.base.TimeScopeNotRequiredPageParam;
import com.yezi.luframe.mongodb.AdminUserOperateLog;

import java.util.List;

/**
 * @author yezi
 * @date 2019/3/11 14:52
 */
public interface AdminUserOperateLogService {

    /**
     * 保存用户操作日志
     *
     * @param operateLog
     * @return
     */
    AdminUserOperateLog addAdminUserOperateLog(AdminUserOperateLog operateLog);

    /**
     * 根据id删除用户操作日志
     *
     * @param id
     */
    void deleteAdminUserOperateLog(String id);

    /**
     * 更新用户操作日志
     *
     * @param operateLog
     * @return
     */
    AdminUserOperateLog updateAdminUserOperateLog(AdminUserOperateLog operateLog);

    /**
     * 根据id查找用户操作日志
     *
     * @param id
     * @return
     */
    AdminUserOperateLog findAdminUserOperateLogById(String id);

    /**
     * 查询所有用户操作日志
     *
     * @return
     */
    List<AdminUserOperateLog> findAllAdminUserOperateLog();

    /**
     * 用户操作日志列表查询（分页）
     *
     * @param searchParam
     * @return
     */
    PageInfoData<AdminUserOperateLog> listAdminUserOperateLog(TimeScopeNotRequiredPageParam searchParam);

}
