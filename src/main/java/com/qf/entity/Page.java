package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {

    /**
     * 当前页数
     */
    private Long page;

    /**
     * 当前页面展示条数
     */
    private Long size;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 总条数
     */
    private Long totalSize;

    /**
     * 每一页展示的数据
     */
    private List<T> dataList;
}
