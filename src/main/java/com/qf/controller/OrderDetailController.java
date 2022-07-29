package com.qf.controller;

import com.qf.entity.OrderDetail;
import com.qf.entity.Orders;
import com.qf.service.OrderDetailService;
import com.qf.service.impl.OrderDetailServiceImpl;
import com.qf.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/orderDetail")
public class OrderDetailController extends BaseController{

    private OrderDetailService orderDetailService=new OrderDetailServiceImpl();

    public String detail(HttpServletRequest req, HttpServletResponse resp){
        String oid = req.getParameter("oid");
       Orders orders= orderDetailService.getOrderGoodsById(oid);
       req.setAttribute("order",orders);
        return Constants.FORWARD + "orderDetail.jsp";
    }
}
