package com.zsw.pattern.singleton;

import java.io.Serializable;
import java.util.Objects;

/**
 * 无法被任何方式破坏单例
 *
 * @author ZhangShaowei on 2019/3/19 15:16
 **/
public class SafeTarget implements Serializable {

    private static final long serialVersionUID = 7438017164661092481L;

    private SafeTarget() {
        // 防止反射破坏单例
        if (Objects.nonNull(TargetHolder.INSTANCE)) {
            throw new RuntimeException("请不要破坏单例");
        }
    }

    public static SafeTarget getInstance() {
        return TargetHolder.INSTANCE;
    }


    private static class TargetHolder {
        private static final SafeTarget INSTANCE = new SafeTarget();
    }

    /**
     * 防止序列化破坏单例
     *
     * 就算使用了 readResolve 方法，实际上在反序列化后也会实例化一次对象，但是没有返回而已，也浪费了内存
     * 实际结果是反悔了 readResolve 返回的对象
     *
     * 接下来请关注 注册式单例
     *
     * @return
     */
    public Object readResolve() {
        return TargetHolder.INSTANCE;
    }




}
