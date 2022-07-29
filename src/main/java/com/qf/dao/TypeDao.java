package com.qf.dao;

import com.qf.entity.Goods;
import com.qf.entity.Type;

import java.util.List;

public interface TypeDao {


    List<Type> getGoodsType();

    List<Goods> getGoodsDetails(String tid);

    List<Goods> getGoodsDetails();
}
