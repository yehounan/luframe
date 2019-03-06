package com.yezi.luframe.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoData<T> {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 当前页list
     */
    private List<T> list;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 其它数据
     */
    private Object data;

    public PageInfoData(Page<T> pageInfo) {
        this.total = pageInfo.getTotalElements();
        this.list = pageInfo.getContent();
        this.pages = pageInfo.getTotalPages();
    }
}
