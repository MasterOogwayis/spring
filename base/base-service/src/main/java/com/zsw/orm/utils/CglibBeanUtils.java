package com.zsw.orm.utils;

import org.springframework.cglib.beans.BeanCopier;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangshaowei on 2023/7/21 10:53
 */
public class CglibBeanUtils {

    private static ConcurrentHashMap<BeanCopierKey, BeanCopier> map = new ConcurrentHashMap<BeanCopierKey, BeanCopier>();

    public static <S, T> T copy(S source, T target) {
        if (source == null || target == null) {
            return null;
        }
        Class<?> srcType = source.getClass();
        Class<?> targetClass = target.getClass();
        BeanCopierKey key = new BeanCopierKey(srcType, targetClass);
        BeanCopier copier = map.get(key);
        if (copier == null) {
            map.putIfAbsent(key, BeanCopier.create(srcType, targetClass, false));
            copier = map.get(key);
        }
        try {
            copier.copy(source, target, null);
        } catch (Exception e) {
            System.out.println("Bean对象复制失败: " + key);
        }
        return target;
    }

    static class BeanCopierKey {

        Class<?> srcType;
        Class<?> targetType;

        public BeanCopierKey(Class<?> srcType, Class<?> targetType) {
            this.srcType = srcType;
            this.targetType = targetType;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof BeanCopierKey)) {
                return false;
            }
            BeanCopierKey key = (BeanCopierKey) obj;
            return key.srcType == srcType && key.targetType == targetType;
        }

        @Override
        public int hashCode() {
            return srcType.hashCode() + targetType.hashCode();
        }

        @Override
        public String toString() {
            return srcType + " -> " + targetType;
        }

    }

}
