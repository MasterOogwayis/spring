package com.zsw.mq.spring.api;

/**
 * @author ZhangShaowei on 2019/9/12 12:48
 **/
public interface MessageListener<T> {

    void consume(final T message);

}
