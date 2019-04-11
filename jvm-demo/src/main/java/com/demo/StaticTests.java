package com.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author ZhangShaowei on 2019/2/27 14:15
 **/
@Slf4j
public class StaticTests {


    @SneakyThrows
    public static void main(String[] args) {

        String str = "123456789";
        System.out.println(str.substring("123".length()));
    }


    @Test
    public void test3() {
        int i = 0;
        long j = 1L;
        char c = 'a';
        double d = 2D;
        System.out.println(i + j + c + d);
    }


    static class A {
        public A() {
            System.out.println("A init ...");
        }

    }

    static class B extends A {
        public B() {
            System.out.println("B init ...");
        }
    }

    static class C extends B {
        public C() {
            System.out.println("C init ...");
        }
    }


}
