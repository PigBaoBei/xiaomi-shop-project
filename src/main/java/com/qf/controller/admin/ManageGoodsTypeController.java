package com.qf.controller.admin;

import com.qf.controller.BaseController;
import com.qf.entity.Type;
import com.qf.service.admin.ManageGoodsTypeService;
import com.qf.service.impl.admin.ManageGoodsTypeServiceImpl;
import com.qf.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/manageGoodsType")
public class ManageGoodsTypeController extends BaseController {

    private ManageGoodsTypeService manageGoodsTypeService=new ManageGoodsTypeServiceImpl();

    public String show(HttpServletRequest req, HttpServletResponse resp){
        List<Type> goodsTypeList= manageGoodsTypeService.getAllGoodsType();
        req.setAttribute("goodsTypeList",goodsTypeList);
        return Constants.FORWARD+"admin/showGoodsType.jsp";
    }

    public String add(HttpServletRequest req, HttpServletResponse resp){
        List<Type> goodsTypesList=manageGoodsTypeService.getGoodsType();
        req.setAttribute("goodsTypeList",goodsTypesList);
        return Constants.FORWARD+"admin/addGoodsType.jsp";
    }
}
