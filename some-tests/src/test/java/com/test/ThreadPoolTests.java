package com.test;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.zsw.function.ThrowableSupplier.execute;


/**
 * @author ZhangShaowei on 2022/2/15 16:02
 */
public class ThreadPoolTests {




    public static void main(String[] args) {

        Object o1 = new Object();
        Object o2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (o1) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("can`t get in");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (o2) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("can`t get in");
                }
            }
        });

        thread1.start();
        thread2.start();

    }


    private static void test() {
        var executor = new ThreadPoolExecutor(
                1,
                1,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());


        Future<String> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Hello World";
        });


        String result = execute(future::get);

        System.out.println(result);
        executor.shutdown();
    }

}
