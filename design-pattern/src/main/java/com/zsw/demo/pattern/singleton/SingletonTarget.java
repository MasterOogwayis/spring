package com.zsw.demo.pattern.singleton;

import java.io.Serializable;

/**
 * @author Shaowei Zhang on 2019/3/9 19:32
 **/
public class SingletonTarget implements Serializable {

    private static final SingletonTarget INSTANCE = new SingletonTarget();

    private SingletonTarget() {
    }


    public static SingletonTarget getInstance() {
        return INSTANCE;
    }

    public Object readResolve() {
        return INSTANCE;
    }

}
