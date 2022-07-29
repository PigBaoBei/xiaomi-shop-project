package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    private static final long serialVersionUID = -5958818914700240821L;
    private int id;
    private String oid; //订单id
    private int pid;    //商品id
    private int money; //小计
    private int num;    //购买数量
    private Goods goods;

}
