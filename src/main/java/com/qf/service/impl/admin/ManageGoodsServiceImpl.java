package com.qf.service.impl.admin;

import com.qf.dao.admin.ManageGoodsDao;
import com.qf.dao.impl.admin.ManageGoodsDaoImpl;
import com.qf.entity.Goods;
import com.qf.entity.Type;
import com.qf.service.admin.ManageGoodsService;

import java.util.List;

public class ManageGoodsServiceImpl implements ManageGoodsService {

    private ManageGoodsDao manageGoodsDao=new ManageGoodsDaoImpl();


    @Override
    public List<Goods> getAllGoods() {
        List<Goods> goods = manageGoodsDao.getAllGoods();
        for (Goods good : goods) {
           Type type= manageGoodsDao.getGoodType(Integer.parseInt(good.getTid()));
           good.setTypeName(type.getName());
        }
        return goods;
    }
}
