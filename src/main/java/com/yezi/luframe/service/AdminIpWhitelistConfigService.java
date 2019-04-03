package com.yezi.luframe.service;



import com.yezi.luframe.entity.AdminIpWhitelistConfig;
import com.yezi.luframe.param.AdminIpWhitelistConfigPageParam;
import com.yezi.luframe.param.AdminIpWhitelistConfigParam;
import com.yezi.luframe.vo.BaseVO;
import com.yezi.luframe.vo.ExtendVO;

import java.util.List;

/**
 * @author humb
 */
public interface AdminIpWhitelistConfigService {

    /**
     * 添加ip白名单
     *
     * @param param
     * @return
     */
    BaseVO insert(AdminIpWhitelistConfigParam param);

    /**
     * 删除ip白名单
     *
     * @param id
     * @return
     */
    BaseVO delete(Long id);

    /**
     * 分页查询白名单信息
     *
     * @param param
     * @return
     */
    ExtendVO findByPage(AdminIpWhitelistConfigPageParam param);

    /**
     * 白名单列表
     *
     * @return
     */
    List<AdminIpWhitelistConfig> findAll();

    /**
     * 获取ip列表
     *
     * @return
     */
    List<String> getIps();

}
