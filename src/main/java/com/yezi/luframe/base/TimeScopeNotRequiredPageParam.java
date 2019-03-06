package com.yezi.luframe.base;

import lombok.Data;

import java.util.Date;

/**
 * @author yezi
 * @date 2019/3/6 10:43
 */
@Data
public class TimeScopeNotRequiredPageParam extends PageParam {

    /**
     * 时间范围起始时间
     */
    private Date startPoint;

    /**
     * 时间范围截止时间
     */
    private Date endPoint;
}
