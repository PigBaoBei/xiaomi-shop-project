package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {

    private Integer id;

    private String name;

    private Date pubdate;

    private String picture;

    private Integer price;

    private Integer star;       //商品的热门指数

    private String intro;   //商品的描述

    private String tid;

    private String typeName;

}
