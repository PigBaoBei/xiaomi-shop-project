package com.qf.service;

import com.qf.entity.OrderDetail;
import com.qf.entity.Orders;

import java.util.List;

public interface OrderService {

    boolean addOrder(Orders orders);

    List<Orders> getOrder(String uid);

}
