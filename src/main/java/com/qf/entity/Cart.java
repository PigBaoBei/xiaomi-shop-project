package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private Integer cid;

    private Integer uid;

    private Integer pid;

    private Integer num;

    private Integer money;

    private Goods goods;
}
