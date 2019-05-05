package com.demo;

import lombok.SneakyThrows;

/**
 * @author ZhangShaowei on 2019/4/30 10:32
 **/
public class TestNative {

    public static void main(String[] args) throws Exception {


        for (int i = 0; i < 2000; i++) {
            new Thread(()-> {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


        Thread.sleep(10 * 60 * 10000);
    }

}
