package com.demo.spring.ioc.overview.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ZhangShaowei on 2021/4/23 14:50
 */
@Getter
@Setter
@ToString
@Component
public class User {

    @Autowired
    private Long id;

    @Resource(name = "name")
    private String name;

}
