package com.qf.service;

import com.qf.entity.Goods;
import com.qf.entity.Page;

public interface GoodsService {


    Page<Goods> getGoodsDetails(String tid, String page, String size);

    Page<Goods> getGoodsDetails( String page, String size);

    Goods getGoodsById(String id);
}
