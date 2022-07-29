package com.qf.service.impl.admin;

import com.qf.dao.admin.ManageOrderDao;
import com.qf.dao.impl.admin.ManageOrderDaoImpl;
import com.qf.entity.Orders;
import com.qf.entity.User;
import com.qf.service.admin.ManageOrderService;

import java.util.List;

public class ManageOrderServiceImpl implements ManageOrderService {

    private ManageOrderDao manageOrderDao=new ManageOrderDaoImpl();

    @Override
    public List<Orders> getAllOrder() {
        List<Orders> orders = manageOrderDao.getAllOrder();
        for (Orders order : orders) {
            User user=manageOrderDao.getOrderUsername(order.getUid());
            order.setUsername(user.getUsername());
        }
        return orders;
    }
}
