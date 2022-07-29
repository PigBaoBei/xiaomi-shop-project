package com.qf.dao;

import com.qf.entity.*;

import java.util.List;

public interface OrderDetailDao {
    Orders getOrderById(String oid);

    Address getAddressById(int aid);

    List<OrderDetail> getOrderDetailById(String oid);

    Goods getGoodById(int pid);

    void addOrderDetail(String orderId, Integer pid, Integer num, Integer money);
}
