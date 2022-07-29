package com.qf.dao.admin;

import com.qf.entity.Goods;
import com.qf.entity.Type;

import java.util.List;

public interface ManageGoodsDao {

    List<Goods> getAllGoods();

    Type getGoodType(int tid);
}
