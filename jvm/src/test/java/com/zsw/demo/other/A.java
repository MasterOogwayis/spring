package com.zsw.demo.other;

/**
 * @author Shaowei Zhang on 2022/2/27 19:18
 */
class A implements B, C {

    public static void main(String[] args) {
        new A().s();

        new D(){};
    }

    @Override
    public void s() {
        C.super.s();
    }
}
