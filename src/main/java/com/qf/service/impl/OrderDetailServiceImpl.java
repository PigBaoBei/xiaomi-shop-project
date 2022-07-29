package com.qf.service.impl;

import com.qf.dao.OrderDetailDao;
import com.qf.dao.impl.OrderDetailDaoImpl;
import com.qf.entity.*;
import com.qf.service.OrderDetailService;

import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {

    private OrderDetailDao orderDetailDao=new OrderDetailDaoImpl();

    @Override
    public Orders getOrderGoodsById(String oid) {
        //先根据订单id查询到aid
        Orders orders=orderDetailDao.getOrderById(oid);
        //根据订单id查询到地址id
        Address address=orderDetailDao.getAddressById(orders.getAid());
        //根据查询出来的条件去查询到订单的详细信息
        List<OrderDetail> orderDetails=orderDetailDao.getOrderDetailById(oid);
        for (OrderDetail orderDetail : orderDetails) {
            Goods goods=orderDetailDao.getGoodById(orderDetail.getPid());
            orderDetail.setGoods(goods);
        }
        orders.setAddress(address);
        orders.setOrderDetails(orderDetails);
        return orders;
    }

    @Override
    public void batchOrderDetail(String orderId, List<Cart> carts) {
        for (Cart cart : carts) {
            orderDetailDao.addOrderDetail(orderId,cart.getPid(),cart.getNum(),cart.getMoney());
        }

    }
}
