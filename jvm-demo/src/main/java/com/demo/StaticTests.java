package com.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.servlet.HandlerMapping;

import java.time.LocalDateTime;
import java.util.Vector;

/**
 * @author ZhangShaowei on 2019/2/27 14:15
 **/
@Slf4j
public class StaticTests {


    @SneakyThrows
    public static void main(String[] args) {

        System.out.println(LocalDateTime.now().withSecond(0));
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{nums[i], nums[j]};
                }
            }
        }
        throw new IllegalArgumentException("Not Found!");
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
