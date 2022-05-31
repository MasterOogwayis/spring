package com.tests;

import java.util.HashSet;

/**
 * @author Shaowei Zhang on 2022/3/20 10:45
 */
public class SetTests {

    public static void main(String[] args) {
        HashSet<A> set = new HashSet<>();
        System.out.println(set.add(new A()));
        System.out.println(set.add(new A()));
        System.out.println(set.size());


    }


    private static class A {
        @Override
        public int hashCode() {
            return 1;
        }
    }

}
