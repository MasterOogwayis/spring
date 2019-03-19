package com.zsw.demo.pattern.singleton;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * @author Shaowei Zhang on 2019/3/9 19:33
 **/
public class TestSingleton {

    @SneakyThrows
    public static void main(String[] args) {

        SingletonTarget target = SingletonTarget.getInstance();

        // 1. 反射破坏单例
//        Constructor<SingletonTarget> declaredConstructor = SingletonTarget.class.getDeclaredConstructor();
//        declaredConstructor.setAccessible(true);
//        SingletonTarget target1 = declaredConstructor.newInstance();
//        System.out.println(target.equals(target1)); false

        // 2. 序列化破坏单例
//        String path = "SingletonTarget.object";
//        @Cleanup ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
//        oos.writeObject(target);
//
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
//        SingletonTarget object = (SingletonTarget) ois.readObject();
//        System.out.println(target.equals(object)); // false


        @Cleanup ByteArrayOutputStream bos = new ByteArrayOutputStream();
        @Cleanup ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(target);
        @Cleanup ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        SingletonTarget object = (SingletonTarget) ois.readObject();
        System.out.println(target.equals(object));

    }

}
