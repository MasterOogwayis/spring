package com.tests;

import com.zsw.pojo.MainOrder;

/**
 * @author Shaowei Zhang on 2022/3/9 16:23
 */
public class LombokTests {

    public static void main(String[] args) {
        System.out.println(MainOrder.Fields.name);
        System.out.println(MainOrder.Fields.price);
        System.out.println(MainOrder.Fields.address);
    }

}
