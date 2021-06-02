package com.zsw.lesson.l28;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2021/6/1 14:27
 */
public class HotCode {

    private static volatile int value = 0;

    private static Object array;

    public static void main(String[] args) {
        long timer = System.currentTimeMillis();
        long deadline = TimeUnit.SECONDS.toMillis(10);
        while (System.currentTimeMillis() - timer <= deadline) {
            hotMethod1();
            hotMethod2();
            hotMethod3();
            allocate();
        }
    }

    private static void allocate() {
        array = new int[6 * 1000];
        array = new Integer[6 * 1000];
    }

    private static void hotMethod3() {
        ArrayList<String> list = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replace("-", "");
        list.add(str);

    }

    private static void hotMethod2() {
        value ++;
    }

    private static void hotMethod1() {
        int anInt = new Random().nextInt();
    }
    

}
