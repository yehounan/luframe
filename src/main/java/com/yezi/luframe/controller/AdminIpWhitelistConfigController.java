package com.yezi.luframe.controller;


import com.yezi.luframe.constant.URIConstants;
import com.yezi.luframe.param.AdminIpWhitelistConfigPageParam;
import com.yezi.luframe.param.AdminIpWhitelistConfigParam;
import com.yezi.luframe.service.AdminIpWhitelistConfigService;
import com.yezi.luframe.util.AdminLoginUserUtils;
import com.yezi.luframe.util.VOUtils;
import com.yezi.luframe.vo.BaseVO;
import com.yezi.luframe.vo.ExtendVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * ip白名单管理
 *  @author humb
 */
@RestController
@Slf4j
public class AdminIpWhitelistConfigController {

    @Autowired
    private AdminIpWhitelistConfigService aminIpWhitelistConfigService;


    @PostMapping(URIConstants.AdminIpWhitelistConfigURL.INSERT)
    public BaseVO insert(@RequestBody AdminIpWhitelistConfigParam param, HttpServletRequest request){
        if(param!=null){
            param.setCreatorId(AdminLoginUserUtils.getAdminUserId(request));
            param.setCreator(AdminLoginUserUtils.getAdminUsername(request));
        }
        return aminIpWhitelistConfigService.insert(param);
    }


    @GetMapping(URIConstants.AdminIpWhitelistConfigURL.DELETE)
    public BaseVO delete(Long id){
        return aminIpWhitelistConfigService.delete(id);
    }


    @PostMapping(URIConstants.AdminIpWhitelistConfigURL.PAGE)
    public ExtendVO findByPage(@RequestBody AdminIpWhitelistConfigPageParam param){
        return aminIpWhitelistConfigService.findByPage(param);
    }

    @GetMapping("/ipwhitelist/ips")
    public ExtendVO getIps(){
        List<String> list=aminIpWhitelistConfigService.getIps();
        return VOUtils.returnExtendVOSuccess(list);
    }
}
