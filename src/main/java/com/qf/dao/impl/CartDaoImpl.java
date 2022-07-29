package com.qf.dao.impl;

import com.qf.dao.CartDao;
import com.qf.entity.Cart;
import com.qf.entity.Goods;
import com.qf.utils.DBUtil;
import com.qf.vo.CartVo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CartDaoImpl implements CartDao {

    private QueryRunner queryRunner = new QueryRunner(DBUtil.getDateSource());

    @Override
    public int addShop(Cart cart) {

        String sql = "INSERT INTO tb_cart (uid,pid,num,money) VALUES(?,?,?,?)";
        try {
            return queryRunner.update(sql,
                    cart.getUid(),
                    cart.getPid(),
                    cart.getNum(),
                    cart.getMoney());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Goods getGoodsById(Cart cart) {
        Integer pid = cart.getPid();
        String sql="SELECT * FROM tb_goods WHERE id=?";
        try {
            return queryRunner.query(sql,new BeanHandler<>(Goods.class),pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cart> getCart(String uid) {
        String sql="SELECT * FROM tb_cart WHERE uid=?";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Cart.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException("查询购物车失败");

        }
    }

    @Override
    public int deleteCart(int cid) {
        String sql="DELETE FROM tb_cart WHERE cid=?";
        try {
           return queryRunner.update(sql,cid);
        } catch (SQLException e) {
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public int updateCartInfo(int cid, int price, int num) {
        String sql="UPDATE tb_cart SET num=? ,money=? WHERE cid=?";
        try {
          return   queryRunner.update(sql,num,(num*price),cid);
        } catch (SQLException e) {
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public int clearCart(String uid) {
        String sql="DELETE FROM tb_cart WHERE uid=?";
        try {
           return queryRunner.update(sql, uid);
        } catch (SQLException e) {
            throw new RuntimeException("清空失败");
        }
    }

    @Override
    public List<CartVo> getCartVo(long uid) {

        String sql="SELECT a.cid,b.`name`,a.num,b.price,a.money FROM tb_cart a LEFT JOIN tb_goods b ON a.pid=b.id WHERE uid=?";
        try {
           return queryRunner.query(sql,new BeanListHandler<>(CartVo.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException("清空失败");
        }
    }

    @Override
    public List<Cart> getCartList(int uid) {
        String sql="SELECT * FROM tb_cart WHERE uid=?";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Cart.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException("查询购物车信息失败");
        }
    }

}
