package com.zsw.listener;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2020/2/11 15:41
 */
public interface QueueMessageListener<T extends Serializable> {

    void onMessage(T t);

}
