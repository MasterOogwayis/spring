package com.zsw.pattern.singleton;

import com.zsw.pattern.IOUtils;
import com.zsw.pattern.singleton.lazy.LazyTarget;
import com.zsw.pattern.singleton.register.EnumSingleton;

/**
 * @author ZhangShaowei on 2019/3/19 15:54
 **/
public class TestEnumSingleton {

    public static void main(String[] args) {

        EnumSingleton instance = EnumSingleton.getInstance();
        instance.setData(LazyTarget.getInstance());

        String path = "EnumSingleton.obj";

        IOUtils.writeObject(instance, path);

        EnumSingleton object = (EnumSingleton) IOUtils.readObject(path);

        System.out.println(instance.getData());
        System.out.println(object.getData());
        System.err.println(instance.getData().equals(object.getData()));


    }

}
