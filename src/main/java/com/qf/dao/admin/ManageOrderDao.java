package com.qf.dao.admin;

import com.qf.entity.Orders;
import com.qf.entity.User;

import java.util.List;

public interface ManageOrderDao {

    List<Orders> getAllOrder();

    User getOrderUsername(int uid);
}
