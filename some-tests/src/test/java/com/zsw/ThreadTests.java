package com.zsw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2022/2/15 14:20
 */
public class ThreadTests {


    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();


        Thread thread1 = new Thread(() -> {
            System.out.println("只有守护线程 jvm 会直接退出");
            try {
                TimeUnit.SECONDS.sleep(20);
                System.out.println("Never comes here!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.setDaemon(true);
        thread1.start();



    }



    private static int n() {
        int i = 0;
        try {
            i = 1;
            return i;
        } finally {
            i = 2;
        }

    }

}
