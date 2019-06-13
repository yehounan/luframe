package com.yezi.luframe.mapper;


import com.yezi.luframe.base.BaseMapper;
import com.yezi.luframe.entity.AdminResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理资源Mapper
 * @author  Administrator
 * @date   2018/2/2
 */
@Repository
public interface AdminResourceMapper extends BaseMapper<AdminResource> {

    /**
     * 根据资源url获取资源信息
     * @param resourceUrl
     * @return
     */
    AdminResource selectByResourceUrl(String resourceUrl);

    /**
     * 根据父id获取菜单列表
     * @param parentId
     * @return
     */
    List<AdminResource> selectMenuByParentId(Long parentId);

    /**
     * 获顺序取菜单列表
     * @return
     */
    List<AdminResource> selectAllOrderBySort();
}