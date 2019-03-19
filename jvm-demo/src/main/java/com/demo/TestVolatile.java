package com.demo;

/**
 * @author Shaowei Zhang on 2019/3/3 20:52
 **/
public class TestVolatile {

    private volatile static int i = 0;

    private static final int THREAD_COUNT = 20;

    public static void increase() {
        i++;
    }


    public static void main(String[] args) {
        long timer = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int j = 0; j < THREAD_COUNT; j++) {
            threads[j] = new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    increase();
                }
            });
            threads[j].start();
        }


        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(i);
        System.err.println(System.currentTimeMillis() - timer);

    }

}
