package com.qf.service;

import com.qf.entity.Cart;
import com.qf.entity.OrderDetail;
import com.qf.entity.Orders;

import java.util.List;

public interface OrderDetailService {

    Orders getOrderGoodsById(String oid);

    void batchOrderDetail(String orderId, List<Cart> carts);
}
