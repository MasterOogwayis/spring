package com.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaowei Zhang on 2019/3/3 16:42
 **/
public class TestOOM {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();

        while (true) {
            list.add(new Object());
        }

    }


}
