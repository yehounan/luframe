package com.yezi.luframe.param;


import com.yezi.luframe.base.PageParam;
import lombok.Data;

/**
 * Created by hmb on 2018/6/7.
 * @author hmb
 */
@Data
public class AdminIpWhitelistConfigPageParam extends PageParam {

    private String ip;

    private String beginTime;

    private String endTime;

    private String isWhiteList="yes";
}
