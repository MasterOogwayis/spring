package com.demo;

import lombok.SneakyThrows;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2021/6/3 14:44
 */
public class ArthasTests {

    @SneakyThrows
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                int i = 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9;
            }
        }, "thread1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            while (true) {
                int j = random();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread2");
        thread2.start();

        long timer = System.currentTimeMillis();
        long hour = TimeUnit.HOURS.toMillis(1);
        while (System.currentTimeMillis() - timer <= hour) {
            int k = 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9;
            TimeUnit.SECONDS.sleep(5);
        }
    }

    public static Integer random() {
        return ThreadLocalRandom.current().nextInt();
    }

}
