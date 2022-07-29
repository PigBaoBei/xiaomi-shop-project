package com.qf.service;

import com.qf.entity.Goods;
import com.qf.entity.Type;

import java.util.List;

public interface TypeService {

    List<Type> getGoodsType();

    List<Goods> getGoodsDetails(String tid);

    List<Goods> getGoodsDetails();

}
