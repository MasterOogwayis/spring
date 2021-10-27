package com.demo;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

/**
 * @author ZhangShaowei on 2021/10/27 15:26
 */
public class BagTests {

    public static void main(String[] args) {
        Bag<String> bag = new HashBag<>();

        for (int i = 0; i < 10; i++) {
            bag.add((i % 3) + "");
        }

        System.out.println(bag.getCount(2));


    }

}
