package com.yezi.luframe.base;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author yezi
 * @date 2019/3/6 10:43
 */
@Data
public class PageParam {

    /**
     * 第几页，从1开始
     */
    @NotNull(message = "页码不能为空!")
    @Min(value = 1)
    private Integer pageNum;

    /**
     * 页尺寸
     */
    @NotNull(message = "页尺寸不能为空!")
    @Min(1)
    @Max(500)
    private Integer pageSize;

    /**
     * 排序 例如：id desc
     */
    private String orderBy;

}
