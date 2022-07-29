package com.qf.service.impl;

import com.qf.dao.OrderDao;
import com.qf.dao.impl.OrderDaoImpl;
import com.qf.entity.OrderDetail;
import com.qf.entity.Orders;
import com.qf.service.OrderService;
import com.qf.utils.Constants;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao=new OrderDaoImpl();

    @Override
    public boolean addOrder(Orders orders) {
        orders.setStatus(Integer.parseInt(Constants.ORDER_NO_PAY));

        long time = System.currentTimeMillis();

        return orderDao.addOrder(orders)==1;
    }

    @Override
    public List<Orders> getOrder(String uid) {
        return orderDao.getOrder(Integer.parseInt(uid));
    }

}
