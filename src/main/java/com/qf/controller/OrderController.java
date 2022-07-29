package com.qf.controller;

import com.qf.entity.*;
import com.qf.service.AddressService;
import com.qf.service.CartService;
import com.qf.service.OrderDetailService;
import com.qf.service.OrderService;
import com.qf.service.impl.AddressServiceImpl;
import com.qf.service.impl.CartServiceImpl;
import com.qf.service.impl.OrderDetailServiceImpl;
import com.qf.service.impl.OrderServiceImpl;
import com.qf.utils.Constants;
import com.qf.utils.RandomUtils;
import com.qf.vo.CartVo;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@WebServlet("/order")
public class OrderController extends BaseController {

    private OrderService orderService = new OrderServiceImpl();

    private CartService cartService = new CartServiceImpl();

    private AddressService addressService = new AddressServiceImpl();

    private OrderDetailService orderDetailService=new OrderDetailServiceImpl();
    
    public String show(HttpServletRequest req, HttpServletResponse resp) {
        //获取域对象
        ServletContext servletContext = this.getServletContext();
        User user = (User) servletContext.getAttribute("userNow");
        //获取域对象中的uid属性值
        String uid = String.valueOf(user.getId());
        //利用cartService进行查询，查询结果就应该是order中的视图对象，这个对象包含了购物车:id、num、money商品:name、price、
        List<CartVo> orders = cartService.getCartVo(uid);
        req.setAttribute("cartList", orders);
        //订单页面还有收获地址的展示，所以这里我们还应该处理地址的展示问题
        List<Address> addressList = addressService.getAddressList(uid);
        req.setAttribute("addressList", addressList);
        return Constants.FORWARD + "order.jsp";
    }

    public String create(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> map = req.getParameterMap();
        Orders orders = new Orders();
        try {
            BeanUtils.populate(orders, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String orderId = RandomUtils.createOrderId();
        orders.setId(orderId);
        if (orderService.addOrder(orders)) {
            //当购物车提交订单的时候我们应该往tb_orderdetail表中也提交一份数据，用户后续的订单详情展示
           List<Cart> carts=cartService.getCartList(orders.getUid());
           //获取到了所需信息，向订单详情表添加信息
           orderDetailService.batchOrderDetail(orderId, carts);
           //每添加一次订单就将购物车进行清理
            cartService.clearCart(String.valueOf(orders.getUid()));
            return Constants.FORWARD + "order?method=getOrderList";
        } else {
            return Constants.FORWARD + "order?method=create&uid=" + orders.getUid();
        }
    }

    public String getOrderList(HttpServletRequest req, HttpServletResponse resp){
        ServletContext servletContext = this.getServletContext();
        User user = (User) servletContext.getAttribute("userNow");
        String uid= String.valueOf(user.getId());
        List<Orders> ordersList=orderService.getOrder(uid);
        for (Orders orders : ordersList) {
            int aid = orders.getAid();
         Address add=addressService.gAddressById(aid);
         orders.setAddress(add);
        }
        req.setAttribute("ordersList",ordersList);
        return Constants.FORWARD+"orderList.jsp";
    }


}

