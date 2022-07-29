package com.qf.service.impl;

import com.qf.dao.GoodsDao;
import com.qf.dao.impl.GoodsDaoImpl;
import com.qf.entity.Goods;
import com.qf.entity.Page;
import com.qf.entity.PageBean;
import com.qf.service.GoodsService;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao=new GoodsDaoImpl();

    private PageBean<Goods> bean=new PageBean<>();


    @Override
    public Page<Goods> getGoodsDetails(String tid, String page, String size) {
        //先进行数据格式转换
        //当前页码数
        Page<Goods> goodsData=new Page<>();
        Long currentPage=Long.parseLong(page);
        //当前页面条数
        Long currentSize=Long.parseLong(size);
        //总条数
        Long totalSize=goodsDao.getCurrent(tid);
        //计算总页数:如果总条数模上页面条数刚刚好那么就直接进行除法运算，如果其结果不为0那么就除以后还得+1的页码数
        Long totalPage=(totalSize%currentSize)==0 ? (totalSize/currentSize): (totalSize/currentSize)+1;

        long index = (currentPage - 1)* currentSize;
        //条件获取完毕就应该去数据库进行操作
        List<Goods> goods= goodsDao.getGoodsDetails(tid,index,currentSize);
       //将查询结果设置进容器中
        goodsData.setPage(currentPage);
        goodsData.setSize(currentSize);
        goodsData.setTotalPage(totalPage);
        goodsData.setTotalSize(totalSize);
        goodsData.setDataList(goods);
        bean.setPage(currentPage);
        bean.setSize(currentSize);
        bean.setTotalPage(totalPage);
        bean.setTotalSize(totalSize);
        bean.setTid(tid);
        return goodsData;
    }

    @Override
    public Page<Goods> getGoodsDetails( String page, String size) {
        //先进行数据格式转换
        //当前页码数
        Page<Goods> goodsData=new Page<>();
        Long currentPage=Long.parseLong(page);
        //当前页面条数
        Long currentSize=Long.parseLong(size);
        //总条数
        Long totalSize=goodsDao.getCurrent();
        //计算总页数:如果总条数模上页面条数刚刚好那么就直接进行除法运算，如果其结果不为0那么就除以后还得+1的页码数
        Long totalPage=(totalSize%currentSize)==0 ? (totalSize/currentSize): (totalSize/currentSize)+1;

        long index = (currentPage - 1)* currentSize;
        //条件获取完毕就应该去数据库进行操作
        List<Goods> goods= goodsDao.getGoodsDetails(index,currentSize);
        //将查询结果设置进容器中
        goodsData.setPage(currentPage);
        goodsData.setSize(currentSize);
        goodsData.setTotalPage(totalPage);
        goodsData.setTotalSize(totalSize);
        goodsData.setDataList(goods);
        return goodsData;
    }

    @Override
    public Goods getGoodsById(String id) {
        return goodsDao.getGoodsById(Integer.parseInt(id));
    }
}
