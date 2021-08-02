package com.zsw.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/8/2 9:13
 */
public class GenericDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("Hello");
        list.add("World");


//        list.add(1);


        // 泛型擦写
        List temp = list;
        temp.add(1);
        System.out.println(list);


    }

}
