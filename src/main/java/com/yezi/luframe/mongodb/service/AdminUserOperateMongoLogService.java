package com.yezi.luframe.mongodb.service;

import com.yezi.luframe.base.PageInfoData;
import com.yezi.luframe.base.TimeScopeNotRequiredPageParam;
import com.yezi.luframe.mongodb.AdminUserOperateMongoLog;

import java.util.List;

/**
 * @author yezi
 * @date 2019/3/11 14:52
 */
public interface AdminUserOperateMongoLogService {

    /**
     * 保存用户操作日志
     *
     * @param operateLog
     * @return
     */
    AdminUserOperateMongoLog addAdminUserOperateLog(AdminUserOperateMongoLog operateLog);

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
    AdminUserOperateMongoLog updateAdminUserOperateLog(AdminUserOperateMongoLog operateLog);

    /**
     * 根据id查找用户操作日志
     *
     * @param id
     * @return
     */
    AdminUserOperateMongoLog findAdminUserOperateLogById(String id);

    /**
     * 查询所有用户操作日志
     *
     * @return
     */
    List<AdminUserOperateMongoLog> findAllAdminUserOperateLog();

    /**
     * 用户操作日志列表查询（分页）
     *
     * @param searchParam
     * @return
     */
    PageInfoData<AdminUserOperateMongoLog> listAdminUserOperateLog(TimeScopeNotRequiredPageParam searchParam);

}
