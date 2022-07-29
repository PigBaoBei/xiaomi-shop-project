package com.qf.controller;

import com.qf.entity.Goods;
import com.qf.entity.Page;
import com.qf.entity.PageBean;
import com.qf.service.GoodsService;
import com.qf.service.impl.GoodsServiceImpl;
import com.qf.utils.Constants;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/goods")
public class GoodController extends BaseController {

    private GoodsService goodsService=new GoodsServiceImpl();

    private PageBean<Goods> bean=new PageBean<>();

    /**
     * 展示商品
     * @param req
     * @param resp
     * @return
     */
    public String show(HttpServletRequest req, HttpServletResponse resp) {
        //拿取前面页面所需要展示页面数
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        String tid = req.getParameter("tid");
        String currentPage = req.getParameter("currentPage");
        ServletContext servletContext = this.getServletContext();
        if (currentPage!=null && !"".equals(currentPage)){
            page=currentPage;
        }else {
            //如果没有获取到前端页面的page属性，我们默认跳转到商品的第一页
            if ("".equals(page) || page == null) {
                page = "1";
            }
        }
        //没有设置当页显示条数我们就默认为一页展示四条商品信息
        if ("".equals(size) || size == null){
            size="4";
        }
        //这里拿取到了所需要的属性，我们就应该调用业务层处理业务
        Page<Goods> goods=goodsService.getGoodsDetails(tid,page,size);
        bean.setTid(tid);
        bean.setPage(Long.parseLong(page));
        //将获取得到的商品信息存储进域对象
        servletContext.setAttribute("pageBean",goods);
        req.setAttribute("pp",tid);
        return Constants.FORWARD+"goodsList.jsp";
    }


    /**
     * 展示商品详情页
     * @param req
     * @param resp
     * @return
     */
    public String detail(HttpServletRequest req, HttpServletResponse resp){
       //获取参数
        String id = req.getParameter("id");
        //拿取参数后就调用业务层处理业务
        Goods goodsInfo = goodsService.getGoodsById(id);
        req.setAttribute("goods",goodsInfo);
        return Constants.FORWARD+"goodsDetail.jsp";
    }

    public String goBack(HttpServletRequest req, HttpServletResponse resp){
        return Constants.FORWARD+"/goods?method=show&tid="+bean.getTid()+"&page="+bean.getPage();
    }



}
