package com.zsw.pattern.singleton;

import com.zsw.pattern.singleton.lazy.LazyDoubleCheckTarget;

/**
 * 单例模式
 *
 * @author ZhangShaowei on 2019/3/19 14:10
 **/
public class TestSingleton {

    public static void main(String[] args) {



        Runnable runnable = () -> {
            System.out.println(LazyDoubleCheckTarget.getInstance());
        };

        for (int i = 0; i < 100; i++) {
            new Thread(runnable).start();
        }

    }

}
