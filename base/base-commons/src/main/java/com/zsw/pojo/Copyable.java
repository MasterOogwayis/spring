package com.zsw.pojo;

/**
 * @author ZhangShaowei on 2020/10/9 14:10
 */
public interface Copyable<T> {

    /**
     * @param source
     * @param target
     * @return T
     */
    T copyProperties(Object source, T target);


    /**
     * @param source
     * @param clazz
     * @return
     */
    T copyProperties(Object source, Class<T> clazz);

}
