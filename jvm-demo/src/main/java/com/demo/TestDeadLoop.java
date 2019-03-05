package com.demo;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 测试死循环，使用jstack 分析原因
 *
 * @author Shaowei Zhang on 2019/3/4 20:52
 **/
public class TestDeadLoop {

    public static void createBusyThread() {
        new Thread(() -> {
            while (true)
                ;
        }, "busyThread").start();
    }

    public static void createLockThread(Object lock) {
        new Thread(() ->{
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "lockThread").start();

    }


    @SneakyThrows
    public static void main(String[] args) {
        @Cleanup BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        bufferReader.readLine();
        createBusyThread();
        bufferReader.readLine();
        Object obj = new Object();
        createLockThread(obj);
        bufferReader.readLine();
        obj.notifyAll();
    }

}
