package com.zsw.mq.spring.api;

import org.springframework.context.Lifecycle;
import org.springframework.messaging.Message;

/**
 * @author ZhangShaowei on 2019/9/11 16:21
 **/
public interface Producer extends Lifecycle {

    /**
     * @param topic
     * @param tags
     * @param data
     */
    void sendOneway(String topic, String tags, Object data);

    /**
     * @param topic
     * @param tags
     * @param data
     * @return
     */
    SendResult sendSync(String topic, String tags, Object data);

    /**
     * 异步发送
     *
     * @param topic
     * @param tags
     * @param data
     * @return
     */
    void sendAsync(String topic, String tags, Object data, AsyncCallback callback);

    /**
     * @param topic
     * @param tags
     * @param keys
     * @param data
     */
    void sendOneway(String topic, String tags, String keys, Object data);

    /**
     * @param topic
     * @param tags
     * @param keys
     * @param data
     * @return
     */
    SendResult sendSync(String topic, String tags, String keys, Object data);

    /**
     * 异步发送
     *
     * @param topic
     * @param tags
     * @param keys
     * @param data
     * @param callback
     */
    void sendAsync(String topic, String tags, String keys, Object data, AsyncCallback callback);


    /**
     * @param destination formats: `topicName:tags`
     * @param message     {@link org.springframework.messaging.Message}
     * @return
     */
    SendResult syncSend(String destination, Message<?> message);

    /**
     * @param destination formats: `topicName:tags`
     * @param message     {@link org.springframework.messaging.Message}
     * @param callback    AsyncCallback
     */
    void asyncSend(String destination, Message<?> message, AsyncCallback callback);
}
