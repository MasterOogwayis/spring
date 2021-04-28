package com.demo.spring.dependency.domain;

/**
 * @author ZhangShaowei on 2021/4/28 15:51
 */
@FunctionalInterface
public interface ObjectHolder<T> {

    T getObject();

}
