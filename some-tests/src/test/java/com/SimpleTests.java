package com;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Shaowei Zhang on 2022/3/8 12:09
 */
public class SimpleTests {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (arrayBlockingQueue) {
                arrayBlockingQueue.notifyAll();
            }

        }).start();

        synchronized (arrayBlockingQueue) {

            arrayBlockingQueue.wait();
        }




    }

}
