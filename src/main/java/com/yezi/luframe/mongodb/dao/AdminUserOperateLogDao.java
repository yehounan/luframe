package com.yezi.luframe.mongodb.dao;

import com.yezi.luframe.mongodb.AdminUserOperateLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yezi
 * @date 2019/3/11 14:47
 */
@Repository
public interface AdminUserOperateLogDao extends MongoRepository<AdminUserOperateLog, Long> {
}
