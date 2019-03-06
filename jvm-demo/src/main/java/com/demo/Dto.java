package com.demo;

import lombok.Data;

import java.io.Serializable;

/**
 * 测试 java中创建一个实例的几种方式
 *
 * @author Shaowei Zhang on 2019/3/3 21:54
 **/
@Data
public class Dto implements Serializable {

    private String name;

}
