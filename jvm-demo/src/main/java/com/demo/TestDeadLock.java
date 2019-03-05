package com.demo;

import lombok.SneakyThrows;

import java.util.stream.Stream;

/**
 * 测试死锁，通过工具jstack分析死锁
 *
 *
 * @author ZhangShaowei on 2019/2/28 9:26
 **/
public class TestDeadLock implements Runnable {

    private final byte[] lock1 = new byte[0];
    private final byte[] lock2 = new byte[0];

    public static void main(String[] args) {

        // 1
//        new TestDeadLock().run();

        // 2 Integer [-128, 127] 之间使用的是缓存，所以Integer.valueOf()返回的是同一个缓存中的对象
        for (int i = 0; i < 200; i++) {
            new Thread(new SynAddRunnable(1, 2)).start();
            new Thread(new SynAddRunnable(2, 1)).start();
        }

    }

    @Override
    public void run() {

        new Thread(new LockA(), "A").start();
        new Thread(new LockB(), "B").start();

    }


    class LockA implements Runnable {

        @Override
        @SneakyThrows
        public void run() {
            synchronized (lock1) {
                System.out.println("success lock1");
                Thread.sleep(100);
                System.out.println("try to get lock2");
                synchronized (lock2) {
                    System.out.println("success get lock2");
                }
            }
        }
    }

    class LockB implements Runnable {

        @Override
        @SneakyThrows
        public void run() {
            synchronized (lock2) {
                System.out.println("success lock2");
                Thread.sleep(100);
                System.out.println("try to get lock1");
                synchronized (lock1) {
                    System.out.println("success get lock1");
                }
            }
        }
    }


    static class SynAddRunnable implements Runnable {
        int a,b;

        public SynAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }

}
