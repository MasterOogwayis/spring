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

        synchronized ("123") {

        }

        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        t1.join();

        System.out.println("all done");


    }


}
