package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private Long id;

    private String detail;

    private String name;

    private String phone;

    private Long uid;

    private int state;

    private String method;
}
