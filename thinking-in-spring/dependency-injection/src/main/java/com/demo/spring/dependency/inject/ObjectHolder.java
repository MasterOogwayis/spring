package com.demo.spring.dependency.inject;

/**
 * @author Shaowei Zhang on 2021/5/3 0:11
 */
@FunctionalInterface
public interface ObjectHolder<T> {

    T getObject();

}
