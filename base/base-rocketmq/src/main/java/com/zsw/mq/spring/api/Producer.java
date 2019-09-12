package com.zsw.mq.spring.api;

import org.springframework.context.Lifecycle;
import org.springframework.messaging.Message;

/**
 * @author ZhangShaowei on 2019/9/11 16:21
 **/
public interface Producer extends Lifecycle {

    /**
     * @param topic
     * @param expression
     * @param data
     */
    void sendOneway(String topic, String expression, Object data);

    /**
     * @param topic
     * @param expression
     * @param data
     * @return
     */
    SendResult sendSync(String topic, String expression, Object data);

    /**
     * 异步发送
     *
     * @param topic
     * @param expression
     * @param data
     * @return
     */
    void sendAsync(String topic, String expression, Object data, AsyncCallback callback);


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
