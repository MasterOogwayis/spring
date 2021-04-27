package com.demo.spring.ioc.overview.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.PostConstruct;

/**
 * @author ZhangShaowei on 2021/4/23 14:50
 */
@Getter
@Setter
@ToString
public class User {

    private Long id;

    private String name;

    private String address;




    public static User createUser(){
        return new User();
    }


    @PostConstruct
    public void init(){
        System.out.println("init ...");
    }

}
