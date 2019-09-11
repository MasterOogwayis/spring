package com.anze.mq.spring.support;

/**
 * @author ZhangShaowei on 2019/9/11 16:08
 **/
public interface ContainerFactory<T> {

    T create();

}
