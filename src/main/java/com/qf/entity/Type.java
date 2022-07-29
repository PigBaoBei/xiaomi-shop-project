package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type implements Serializable {

    private static final long serialVersionUID=-5999071849393372450L;

    private Integer id;

    private String name;

    private String level;

    private Integer parent; //这个就是表示父级id，也就是没个父级菜单的子菜单

    private String parentName;
}
