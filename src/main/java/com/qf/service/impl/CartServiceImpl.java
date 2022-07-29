package com.qf.service.impl;

import com.qf.dao.CartDao;
import com.qf.dao.GoodsDao;
import com.qf.dao.impl.CartDaoImpl;
import com.qf.dao.impl.GoodsDaoImpl;
import com.qf.entity.Cart;
import com.qf.entity.Goods;
import com.qf.entity.PageBean;
import com.qf.service.CartService;
import com.qf.vo.CartVo;

import java.util.List;

public class CartServiceImpl implements CartService {

    private CartDao cartDao = new CartDaoImpl();

    private GoodsDao goodsDao = new GoodsDaoImpl();


    @Override
    public boolean addShop(Cart cart) {

        Goods goods = cartDao.getGoodsById(cart);
        cart.setGoods(goods);
        Integer price = goods.getPrice();
        cart.setMoney(price);

        return cartDao.addShop(cart) == 1;
    }

    @Override
    public List<Cart> getCart(String uid) {
        List<Cart> carts = cartDao.getCart(uid);
        //这里我们应该为goods注入值，否则页面不会展示商品
        if (!carts.isEmpty()) {
            //判断是否存在有商品，进行遍历
            for (Cart cart : carts) {
                //拿到购物车中的商品id，然后调用goods的业务层进行商品信息查询
                Integer pid = cart.getPid();
                Goods good = goodsDao.getGoodsById(pid);
                //将得到的商品添加进购物车的List<Goods>属性中
                cart.setGoods(good);
            }
        }

        return carts;
    }

    @Override
    public boolean updateCartInfo(String cid, String price, String num) {
        return cartDao.updateCartInfo(Integer.parseInt(cid), Integer.parseInt(price), Integer.parseInt(num)) == 1;

    }

    @Override
    public boolean deleteCart(String cid) {
        return cartDao.deleteCart(Integer.parseInt(cid)) == 1;
    }

    @Override
    public boolean clearCart(String uid) {
        int result = cartDao.clearCart(uid);
        if (result >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<CartVo> getCartVo(String uid) {
        return cartDao.getCartVo(Long.parseLong(uid));
    }

    @Override
    public List<Cart> getCartList(int uid) {
        return cartDao.getCartList(uid);
    }


}


