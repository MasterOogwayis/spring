package com.zsw.base.beans;

import java.util.function.Supplier;

/**
 * @author ZhangShaowei on 2019/4/15 17:28
 **/
public class BeanUtils extends org.springframework.beans.BeanUtils {


    /**
     * 属性拷贝
     *
     * @param source
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T copyProperties(Object source, Class<T> clazz, String... ignoreProperties) {
        T t = instantiateClass(clazz);
        copyProperties(source, t, ignoreProperties);
        return t;
    }

    public static <T> T copyProperties(Object source, Supplier<T> supplier, String... ignoreProperties) {
        T t = supplier.get();
        copyProperties(source, t, ignoreProperties);
        return t;
    }

    public static <T> T copyNotNullProperties(Object source, Class<T> clazz, String... ignoreProperties) {
        T t = instantiateClass(clazz);
        copyProperties(source, t);
        return t;
    }


}
