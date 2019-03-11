package com.yezi.luframe.mongodb.service.impl;

import com.yezi.luframe.base.PageInfoData;
import com.yezi.luframe.base.TimeScopeNotRequiredPageParam;
import com.yezi.luframe.mongodb.AdminUserOperateLog;
import com.yezi.luframe.mongodb.service.AdminUserOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yezi
 * @date 2019/3/11 14:56
 */
@Service
public class AdminUserOperateLogServiceImpl implements AdminUserOperateLogService {


    @Autowired
    MongoOperations mongoOperations;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminUserOperateLog addAdminUserOperateLog(AdminUserOperateLog operateLog) {
        return mongoOperations.save(operateLog);
    }

    @Override
    public void deleteAdminUserOperateLog(String id) {
        mongoOperations.remove(this.findAdminUserOperateLogById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminUserOperateLog updateAdminUserOperateLog(AdminUserOperateLog operateLog) {
        AdminUserOperateLog dbAdminUserOperateLog = this.findAdminUserOperateLogById(operateLog.getId());
        if (null != dbAdminUserOperateLog) {
            dbAdminUserOperateLog.setClassName(operateLog.getClassName());
            dbAdminUserOperateLog.setLoginIp(operateLog.getLoginIp());
            dbAdminUserOperateLog.setMethodName(operateLog.getMethodName());
            dbAdminUserOperateLog.setParams(operateLog.getParams());
            dbAdminUserOperateLog.setRequestMethod(operateLog.getRequestMethod());
            dbAdminUserOperateLog.setRequestUri(operateLog.getRequestUri());
            dbAdminUserOperateLog.setResult(operateLog.getResult());
            dbAdminUserOperateLog.setUserId(operateLog.getUserId());
            return mongoOperations.save(dbAdminUserOperateLog);
        }
        return null;
    }

    @Override
    public AdminUserOperateLog findAdminUserOperateLogById(String id) {
        return mongoOperations.findById(id, AdminUserOperateLog.class);
    }

    @Override
    public List<AdminUserOperateLog> findAllAdminUserOperateLog() {
        return mongoOperations.findAll(AdminUserOperateLog.class);
    }

    @Override
    public PageInfoData<AdminUserOperateLog> listAdminUserOperateLog(TimeScopeNotRequiredPageParam searchParam) {
        return null;
    }
}
