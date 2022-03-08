package com.zsw.demo.exception;

/**
 * @author Shaowei Zhang on 2022/2/28 14:41
 */
public class ExceptionTests {

    public static void main(String[] args) {
        System.out.println(m1(1));
        System.out.println(m1(2));
    }


    private static int m1(int i) {
        try {
            if ((i & 1) == 1) {
                throw new RuntimeException("奇数");
            } else {
                throw new Error("偶数");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error error) {
            error.printStackTrace();
        }
        return m2();
    }

    private static int m2() {
        return 2;
    }



}
