package com.zsw.pattern.singleton.lazy;

import com.zsw.pattern.DeepCloneable;

import java.io.Serializable;
import java.util.Objects;

/**
 * 静态内部类的单例方式
 *
 * 线程安全，不会预先浪费资源
 *
 * 只有今天内部类初始化才会实例化对象
 * 类触发 类初始化的几种方式：
 * 1. 访问了该类的static 属性，调用他的static方法
 * 2. 使用反射对；类进行调用
 * 3. 子类初始化，父类先一步初始化
 * 4. main方法所在的类，虚拟机启动必定先初始化
 * 5.jdk1.7 动态语言 MethodHandler 解析后是 static 或 invokestatic
 *
 *  *** 通过子类调用父类的 static 字段并不会导致子类被初始化 ***
 *
 *
 * @author ZhangShaowei on 2019/3/19 14:42
 **/
public class LazyInnerClassTarget implements Serializable {

    private static final long serialVersionUID = 5453378701211651066L;

    private LazyInnerClassTarget() {
        // 开启此逻辑，无法被反射破坏单例，但是会被序列化破坏单例
        if (Objects.nonNull(TargetHandler.INSTANCE)) {
            throw new RuntimeException("不允许破坏单例模式");
        }
    }

    private static class TargetHandler {
        private static final LazyInnerClassTarget INSTANCE = new LazyInnerClassTarget();
    }

    public static LazyInnerClassTarget getInstance() {
        return TargetHandler.INSTANCE;
    }

}
