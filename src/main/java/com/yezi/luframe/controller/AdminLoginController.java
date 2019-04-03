package com.yezi.luframe.controller;


import com.yezi.luframe.constant.RedisKeyConstants;
import com.yezi.luframe.constant.URIConstants;
import com.yezi.luframe.param.AdminLoginParam;
import com.yezi.luframe.param.AdminOperateLogQueryParam;
import com.yezi.luframe.redis.RedisCache;
import com.yezi.luframe.service.AdminLoginService;
import com.yezi.luframe.util.TimeUtils;
import com.yezi.luframe.vo.ExtendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc    : 后台管理登录控制器
 * @date    : 2018-01-12
 *
 * @author : yxy
 */
@RestController
public class AdminLoginController  {

    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    RedisCache redisCache;

    /**
     * 后台管理登陆
     * @param adminLoginParam
     * @param httpServletRequest
     * @return
     */
    @PostMapping(URIConstants.LOGIN)
    public ExtendVO login(@Validated @RequestBody AdminLoginParam adminLoginParam, HttpServletRequest httpServletRequest) {
        return adminLoginService.getTokenByAdminUserPassword(adminLoginParam,httpServletRequest);
    }

    /**
     * 后台管理操作日志查询
     * @param adminOperateLogQueryParam
     * @return
     */
    @PostMapping(URIConstants.OPERATELOG_QUERY)
    public ExtendVO loginLogQuery(@RequestBody AdminOperateLogQueryParam adminOperateLogQueryParam) {
        if(adminOperateLogQueryParam.getStartPoint() != null){
            adminOperateLogQueryParam.setStartPoint(TimeUtils.getStartTime(adminOperateLogQueryParam.getStartPoint().getTime()));
        }
        if(adminOperateLogQueryParam.getEndPoint() != null){
            adminOperateLogQueryParam.setEndPoint(TimeUtils.getEndTime(adminOperateLogQueryParam.getEndPoint().getTime()));
        }
        String cacheKey = RedisKeyConstants.ADMIN_OPERATELOG_QUERY+
                (adminOperateLogQueryParam.getPageNum()==null?1:adminOperateLogQueryParam.getPageNum())+"_"+
                (adminOperateLogQueryParam.getPageSize()==null?20:adminOperateLogQueryParam.getPageSize())+"_"+
                (adminOperateLogQueryParam.getUsername()==null?"null":adminOperateLogQueryParam.getUsername().replaceAll(" ","_"))+"_"+
                (adminOperateLogQueryParam.getIp()==null?"null":adminOperateLogQueryParam.getIp().replaceAll(" ","_"))+"_"+
                (adminOperateLogQueryParam.getStartPoint()==null?"null":adminOperateLogQueryParam.getStartPoint().getTime())+"_"+
                (adminOperateLogQueryParam.getEndPoint()==null?"null":adminOperateLogQueryParam.getEndPoint().getTime());
        return redisCache.getCacheValue(cacheKey, 2 * 60, new RedisCache.RedisCacheValueNotFound<ExtendVO>() {
            @Override
            public ExtendVO getValue() {
                return adminLoginService.operateLogQuery(adminOperateLogQueryParam);
            }
        });
    }

    /**
     * 获取管理员自身信息
     * @param httpServletRequest
     * @return
     */
    @GetMapping(URIConstants.SELF_INFO)
    public ExtendVO getSelfInfo(HttpServletRequest httpServletRequest) {
        return adminLoginService.getSelfInfo(httpServletRequest);
    }

}
