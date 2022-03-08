package com.zsw.demo;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author Shaowei Zhang on 2022/2/27 10:46
 */
public class JvmSomeTests {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>(){

        @Override
        protected String initialValue() {
            return "This is a test.";
        }
    };

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new NoClassDefFoundError("");
        }).start();

        TimeUnit.SECONDS.sleep(4);

        System.out.println("end ...");


    }


}
