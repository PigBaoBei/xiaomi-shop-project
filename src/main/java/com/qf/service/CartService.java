package com.qf.service;

import com.qf.entity.Cart;
import com.qf.entity.PageBean;
import com.qf.vo.CartVo;

import java.util.List;

public interface CartService {

    boolean addShop(Cart cart);


    List<Cart> getCart(String uid);

    boolean updateCartInfo(String cid, String price, String num);

    boolean deleteCart(String cid);

    boolean clearCart(String uid);

    List<CartVo> getCartVo(String uid);

    List<Cart> getCartList(int uid);
}
