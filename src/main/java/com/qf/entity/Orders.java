package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    private String method;
    private String id;
    private int uid;    //用户id
    private int aid;    //地址表id
    private int money; //订单总金额
    private Date time; //下单时间
    private int status; //订单状态 0 未付款，1已经付款未发货 2发货待收货 3 收货待评价 4订单完成 5 退货状态
    private List<OrderDetail> orderDetails;
    private Address address;
    private String username;
}
