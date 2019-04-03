package com.yezi.luframe.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.yezi.luframe.dto.AdminUserOperateLogDTO;
import com.yezi.luframe.mapper.AdminUserOperateLogMapper;
import com.yezi.luframe.param.AdminOperateLogQueryParam;
import com.yezi.luframe.service.AdminUserOperateLogService;
import com.yezi.luframe.util.VOUtils;
import com.yezi.luframe.vo.ExtendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yezi
 * @date 2018/9/28 10:08
 */
@Service
public class AdminUserOperateLogServiceImpl implements AdminUserOperateLogService {

    @Autowired
    AdminUserOperateLogMapper adminUserOperateLogMapper;

    @Override
    public ExtendVO getAdminUserOperateLogList(AdminOperateLogQueryParam adminOperateLogQueryParam) {
        PageHelper.startPage(adminOperateLogQueryParam.getPageNum(), adminOperateLogQueryParam.getPageSize());
        List<AdminUserOperateLogDTO> list = adminUserOperateLogMapper.getAdminUserOperateLogList(adminOperateLogQueryParam);
        PageInfo<AdminUserOperateLogDTO> pageInfo = new PageInfo<>(list);
        return VOUtils.returnExtendVOFillPageInfo(pageInfo);
    }
}
