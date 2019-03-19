package com.zsw.design.observer;

import lombok.SneakyThrows;

/**
 * @author Shaowei Zhang on 2019/3/17 22:07
 **/
public class TestObserver {

    @SneakyThrows
    public static void main(String[] args) {

        Teacher teacher = new Teacher("张");

        for (int i = 0; i < 10; i++) {
            teacher.addObserver(new Student(i + ""));
        }


        for (int i = 0; i < 1000; i++) {
            teacher.setHomework("语文");
            Thread.sleep(1000);
        }

    }

}
