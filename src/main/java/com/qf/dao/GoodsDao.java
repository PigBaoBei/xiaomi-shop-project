package com.qf.dao;

import com.qf.entity.Goods;

import java.util.List;

public interface GoodsDao {

    /**
     * 获取当前页面的总条数
     * @param tid
     * @return
     */
    Long getCurrent(String tid);

    Long getCurrent();

    List<Goods> getGoodsDetails(String tid, long index, Long currentSize);

    List<Goods> getGoodsDetails( long index, Long currentSize);

    Goods getGoodsById(int id);
}
