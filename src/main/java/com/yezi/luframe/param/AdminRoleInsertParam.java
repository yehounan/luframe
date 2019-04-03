package com.yezi.luframe.param;


import com.yezi.luframe.dto.AdminResourceDTO;
import lombok.Data;

import java.util.List;

/**
 * Desc    : 角色新增参数
 * @author  : yxy
 * @date    : 2018-02-12
 */
@Data
public class AdminRoleInsertParam {
    private String roleName;

    private List<AdminResourceDTO> resourceList;
}
