package com.demo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ZhangShaowei on 2021/8/3 9:44
 */
public class GenericDemo {

    public static void main(String[] args) {
        Collection<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World!");

        // 编译错误
//        list.add(1L);

        // 泛型擦写
        Collection temp = list;
        temp.add(1L);

        System.out.println(list);




    }

}
