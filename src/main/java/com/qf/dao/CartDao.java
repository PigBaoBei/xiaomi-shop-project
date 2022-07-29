package com.qf.dao;

import com.qf.entity.Cart;
import com.qf.entity.Goods;
import com.qf.vo.CartVo;

import java.util.List;

public interface CartDao {

    /**
     * 购物车添加商品
     * @param cart
     * @return
     */
    int addShop(Cart cart);

    Goods getGoodsById(Cart cart);


    List<Cart> getCart(String uid);

    int deleteCart(int cid);

    int updateCartInfo(int cid, int price, int num);

    int clearCart(String uid);

    List<CartVo> getCartVo(long parseLong);

    List<Cart> getCartList(int uid);
}
