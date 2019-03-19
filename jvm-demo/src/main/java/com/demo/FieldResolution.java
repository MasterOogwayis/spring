package com.demo;

/**
 * @author Shaowei Zhang on 2019/3/12 22:17
 **/
public class FieldResolution {

    interface Interface0{
        public static final int A = 0;
    }

    interface Interface1 extends Interface0{
        public static final int A = 1;
    }

    interface Interface2{
        public static final int A = 3;
    }


    static class Parent implements Interface1 {
        public static final int A = 3;
    }

    static class Sub extends Parent implements Interface2 {
        public static final int A = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }

}
