package com.zsw.test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ZhangShaowei on 2020/4/30 16:43
 */
public class StaticTests {

    private static boolean isAlive;


    public static void main(String[] args) {

        System.out.println(isAlive);

    }


    @Data
    @AllArgsConstructor
    static class Ci {
        private String conditions;
        private String name;
        private String value;
    }

}
