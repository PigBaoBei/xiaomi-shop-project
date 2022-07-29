package com.qf.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 映射order属性的一个视图对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVo {

    private Long cid;

    private String name;

    private Integer num;

    private Integer price;

    private Integer Money;


}
