package com.qf.controller;

import com.qf.entity.JsonResult;
import com.qf.entity.Type;
import com.qf.service.TypeService;
import com.qf.service.impl.TypeServiceImpl;
import com.qf.utils.JSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品列表控制器
 */
@WebServlet("/type")
public class TypeController extends BaseController {

    private TypeService typeService=new TypeServiceImpl();

    public String findAll(HttpServletRequest req, HttpServletResponse resp){
      List<Type> goodsTypes= typeService.getGoodsType();
      return JSON.stringify(JsonResult.success("获取数据成功",goodsTypes));
    }

}
