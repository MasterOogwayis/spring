package com.demo.spring.beans.service;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2021/4/27 9:48
 */
@Data
public class Customer implements Serializable {

    private static final long serialVersionUID = -3607350593378934216L;
    private String name;

    private String phome;


    public Customer getInstance() {
        Customer customer = new Customer();
        customer.setName("客户");
        customer.setName("110");
        return customer;
    }

}
