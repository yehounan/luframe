package com.yezi.luframe.mongodb.service.impl;

import com.yezi.luframe.base.PageInfoData;
import com.yezi.luframe.base.TimeScopeNotRequiredPageParam;
import com.yezi.luframe.mongodb.AdminUserOperateMongoLog;
import com.yezi.luframe.mongodb.service.AdminUserOperateMongoLogService;
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
public class AdminUserOperateMongoLogServiceImpl implements AdminUserOperateMongoLogService {


    @Autowired
    MongoOperations mongoOperations;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminUserOperateMongoLog addAdminUserOperateLog(AdminUserOperateMongoLog operateLog) {
        return mongoOperations.save(operateLog);
    }

    @Override
    public void deleteAdminUserOperateLog(String id) {
        mongoOperations.remove(this.findAdminUserOperateLogById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminUserOperateMongoLog updateAdminUserOperateLog(AdminUserOperateMongoLog operateLog) {
        AdminUserOperateMongoLog dbAdminUserOperateMongoLog = this.findAdminUserOperateLogById(operateLog.getId());
        if (null != dbAdminUserOperateMongoLog) {
            dbAdminUserOperateMongoLog.setClassName(operateLog.getClassName());
            dbAdminUserOperateMongoLog.setLoginIp(operateLog.getLoginIp());
            dbAdminUserOperateMongoLog.setMethodName(operateLog.getMethodName());
            dbAdminUserOperateMongoLog.setParams(operateLog.getParams());
            dbAdminUserOperateMongoLog.setRequestMethod(operateLog.getRequestMethod());
            dbAdminUserOperateMongoLog.setRequestUri(operateLog.getRequestUri());
            dbAdminUserOperateMongoLog.setResult(operateLog.getResult());
            dbAdminUserOperateMongoLog.setUserId(operateLog.getUserId());
            return mongoOperations.save(dbAdminUserOperateMongoLog);
        }
        return null;
    }

    @Override
    public AdminUserOperateMongoLog findAdminUserOperateLogById(String id) {
        return mongoOperations.findById(id, AdminUserOperateMongoLog.class);
    }

    @Override
    public List<AdminUserOperateMongoLog> findAllAdminUserOperateLog() {
        return mongoOperations.findAll(AdminUserOperateMongoLog.class);
    }

    @Override
    public PageInfoData<AdminUserOperateMongoLog> listAdminUserOperateLog(TimeScopeNotRequiredPageParam searchParam) {
        return null;
    }
}
