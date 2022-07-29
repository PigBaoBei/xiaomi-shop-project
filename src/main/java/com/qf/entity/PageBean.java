package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {

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
     * 记录当前用户浏览的商品菜单id
     */
    private String tid;
}
