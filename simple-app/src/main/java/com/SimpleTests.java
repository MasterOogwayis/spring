package com;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2019/2/21 10:44
 **/
public class SimpleTests {


    public static void main(String[] args) {

        List<Object> list = new ArrayList<>();

        int count = 0;
        try {
            while (true) {
                count ++;
                list.add(new byte[1 * 1024 * 1024]);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.err.println(count);
        }


    }


}
