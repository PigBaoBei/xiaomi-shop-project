package com.qf.controller.admin;

import com.qf.controller.BaseController;
import com.qf.entity.Orders;
import com.qf.service.admin.ManageOrderService;
import com.qf.service.impl.admin.ManageOrderServiceImpl;
import com.qf.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/manageOrder")
public class ManageOrderController extends BaseController {

    private ManageOrderService manageOrderService=new ManageOrderServiceImpl();

    public String show(HttpServletRequest req, HttpServletResponse resp){
      List<Orders> ordersList=  manageOrderService.getAllOrder();
      req.setAttribute("orderList",ordersList);
      return Constants.FORWARD+"admin/showAllOrder.jsp";
    }
}
