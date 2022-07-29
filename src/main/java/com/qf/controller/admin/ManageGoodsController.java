package com.qf.controller.admin;

import com.qf.controller.BaseController;
import com.qf.entity.Goods;
import com.qf.service.admin.ManageGoodsService;
import com.qf.service.impl.admin.ManageGoodsServiceImpl;
import com.qf.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/manageGoods")
public class ManageGoodsController extends BaseController {

    private ManageGoodsService manageGoodsService=new ManageGoodsServiceImpl();

    public String show(HttpServletRequest req, HttpServletResponse resp){
       List<Goods> goodsList= manageGoodsService.getAllGoods();
       req.setAttribute("goodsList",goodsList);
        return Constants.FORWARD+"admin/showGoods.jsp";
    }

}
