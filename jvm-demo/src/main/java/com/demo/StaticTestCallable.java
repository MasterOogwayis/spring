package com.demo;

import java.util.concurrent.*;

/**
 * @author ZhangShaowei on 2018/10/10 11:12
 **/
public class StaticTestCallable {


    public static void main(String[] args) throws Exception {

        long mark = System.currentTimeMillis();

        ExecutorService pool = Executors.newFixedThreadPool(2);


        pool.execute(() -> System.out.println(1));

        Future<Long> future1 = pool.submit(() -> test(2100));
        Future<Long> future2 = pool.submit(() -> test(1100));

        System.out.println(future1.get() + future2.get());


        pool.shutdown();

        System.err.println("耗时：" + (System.currentTimeMillis() - mark));

    }


    public static Long test(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return time;

    }



    static class MyCallable implements Callable {

        @Override
        public Object call() throws Exception {
            return null;
        }
    }


}
