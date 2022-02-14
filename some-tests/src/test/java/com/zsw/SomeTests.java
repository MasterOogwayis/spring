package com.zsw;

import com.zsw.utils.IOUtils;

import java.util.ArrayList;

/**
 * @author Shaowei Zhang on 2022/2/14 22:14
 */
public class SomeTests {

    public static void main(String[] args) throws Exception {

        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        System.out.println(list);

        ArrayList<String> copy = IOUtils.copy(list);

        System.err.println(copy);

    }

}
