package com.demo;

import lombok.SneakyThrows;

import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2019/2/28 9:26
 **/
public class TestDeadLock implements Runnable {

    private final byte[] lock1 = new byte[0];
    private final byte[] lock2 = new byte[0];

    public static void main(String[] args) {

        new TestDeadLock().run();
        Thread.getAllStackTraces().forEach((key, value) -> {

            if (!key.equals(Thread.currentThread())) {
                System.out.println("线程 " + key.getName());
                Stream.of(value).forEach(System.out::println);
            }

        });

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


}
