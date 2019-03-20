package com.zsw.pattern.singleton;

import com.zsw.pattern.IOUtils;
import com.zsw.pattern.singleton.lazy.LazyInnerClassTarget;
import lombok.SneakyThrows;

/**
 * 破坏单例
 * <p>
 * 结论： 反射，clone方式都能破坏单例
 *
 * @author ZhangShaowei on 2019/3/19 14:51
 **/
public class BreakSingleton {

    @SneakyThrows
    public static void main(String[] args) {

        // 1. 私有化构造器无法保证单例不会被破坏
//        Constructor<LazyTarget> declaredConstructor = LazyTarget.class.getDeclaredConstructor();
//        declaredConstructor.setAccessible(true);
//        LazyTarget lazyTarget = declaredConstructor.newInstance();
//        LazyTarget instance = LazyTarget.getInstance();
//        System.out.println(lazyTarget);
//        System.err.println(instance);

//        // 2. 序列化破坏单例
//        LazyInnerClassTarget target = LazyInnerClassTarget.getInstance();
//        String path = "Target.obj";
//        IOUtils.writeObject(target, path);
//        LazyInnerClassTarget object = (LazyInnerClassTarget) IOUtils.readObject(path);
//
//        System.err.println(target.equals(object));
//        System.out.println(object);
//        System.out.println(object);

        // 3. SafeTarget 无法被破坏的单例模式

    }

}
