package com.zsw.pattern.singleton.lazy;

import java.util.Objects;

/**
 * 懒加载单例模式，线程不安全
 *
 * Runnable runnable = ()->{
 *     LazyTarget instance = LazyTarget.getInstance();
 *     System.out.println(instance);
 *  };
 *
 *  new Thread(runnable).start();
 *  new Thread(runnable).start();
 *
 * @author ZhangShaowei on 2019/3/19 14:22
 **/
public class LazyTarget {

    private static LazyTarget INSTANCE;


//    public static LazyTarget getInstance() {
//        if (Objects.isNull(INSTANCE)) {
//            INSTANCE = new LazyTarget();
//        }
//        return INSTANCE;
//    }


    public static LazyTarget getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new LazyTarget();
        }
        return INSTANCE;
    }

}
