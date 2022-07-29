package com.qf.dao;

import com.qf.entity.OrderDetail;
import com.qf.entity.Orders;

import java.util.List;

public interface OrderDao {

    int addOrder(Orders orders);

    List<Orders> getOrder(int uid);

}
