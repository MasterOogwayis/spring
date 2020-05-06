package com.zsw.test;

import lombok.NonNull;
import lombok.val;
import lombok.var;

import java.util.Arrays;
import java.util.List;

/**
 * @author ZhangShaowei on 2020/4/30 16:43
 */
public class StaticTests {

    public static void main(String[] args) {

        val name = "zsw";
        name = "Hello World";
        val age = 1;

        test2(name);

    }


    private static void test1() {
        var list = Arrays.asList("1", "2", "3");
        for (val s : list) {
            System.out.println(s);
        }
    }

    private static void test2(@NonNull String name) {
        List<String> list = Arrays.asList("1", "2", "3", name);

        for (String s : list) {
            System.out.println(s);
        }
    }

}
