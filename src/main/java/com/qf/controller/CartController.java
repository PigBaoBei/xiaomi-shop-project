package com.qf.controller;

import com.qf.entity.Cart;
import com.qf.entity.Goods;
import com.qf.entity.PageBean;
import com.qf.entity.User;
import com.qf.service.CartService;
import com.qf.service.impl.CartServiceImpl;
import com.qf.utils.Constants;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/cart")
public class CartController extends BaseController {

    private CartService cartService = new CartServiceImpl();


    public String create(HttpServletRequest req, HttpServletResponse resp) {
        String pid = req.getParameter("pid");
        ServletContext servletContext = this.getServletContext();
        //获取当前登陆的用户
        User user = (User) servletContext.getAttribute("userNow");
        //获取用户id
        Integer id = user.getId();
        Cart cart = new Cart();
        cart.setUid(id);
        cart.setPid(Integer.parseInt(pid));
        cart.setNum(1);

        //这里就获取到了所需的信息，可以将商品进行购物车的添加操作
        boolean result = cartService.addShop(cart);
        if (result) {
            return Constants.FORWARD + "cartSuccess.jsp";
        }
        //失败的情况可能就是没有获取到登陆信息，我们直接返回到主页面
        return Constants.FORWARD + "index.jsp";
    }

    public String show(HttpServletRequest req, HttpServletResponse resp) {
        String uid = req.getParameter("uid");
        //判断当前是否有用户登陆
        if ("".equals(uid) || uid == null) {
            //这里用户没有进行登陆操作，我们应该提醒用户进行登陆
            return Constants.FORWARD + "login.jsp";
        }
        //那么我们就在用户登陆的状态下进行操作
        List<Cart> carts = cartService.getCart(uid);
        req.setAttribute("carts", carts);
        return Constants.FORWARD + "cart.jsp";
    }

    public String update(HttpServletRequest req, HttpServletResponse resp) {
        //从求情中拿到购物车id
        String cid = req.getParameter("cid");
        String price = req.getParameter("price");
        String num = req.getParameter("num");
        ServletContext servletContext = this.getServletContext();
        User user = (User) servletContext.getAttribute("userNow");
        //判断商品的数量，如果只有一个就应该执行删除操作，如果大于一个我们才执行购物车的修改操作

        cartService.updateCartInfo(cid, price, num);
        return Constants.FORWARD + "/cart?method=show&uid=" + user.getId();

    }

    public String delete(HttpServletRequest req, HttpServletResponse resp) {
        String cid = req.getParameter("cid");
        ServletContext servletContext = this.getServletContext();
        User user = (User) servletContext.getAttribute("userNow");
        if ("".equals(cid) || cid == null) {
            return Constants.FORWARD + "message.jsp";
        }
        cartService.deleteCart(cid);
        return Constants.FORWARD + "/cart?method=show&uid=" + user.getId();
    }

    public String clear(HttpServletRequest req, HttpServletResponse resp) {
        //获取到uid
        String uid = req.getParameter("uid");
        //判断是否有uid属性，如果没有那么可能就是用户处于为登陆的状态
        if ("".equals(uid) || uid == null){
            return Constants.FORWARD+"message.jap";
        }
        boolean success=cartService.clearCart(uid);
        if (success){
            return Constants.FORWARD+"/cart?method=show&uid=" +uid;
        }else {
            return "";
        }
    }


}
