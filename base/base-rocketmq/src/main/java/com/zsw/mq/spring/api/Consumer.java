package com.zsw.mq.spring.api;

import org.springframework.context.Lifecycle;

/**
 * 暂时没有主动方法
 *
 * @author ZhangShaowei on 2019/9/12 12:38
 **/
public interface Consumer extends Lifecycle {


//    /**
//     * 订阅消息
//     *
//     * @param topic         消息主题
//     * @param subExpression 订阅过滤表达式字符串，ONS服务器依据此表达式进行过滤。只支持或运算<br>
//     *                      eg: "tag1 || tag2 || tag3"<br>
//     *                      如果subExpression等于null或者*，则表示全部订阅
//     * @param listener      消息回调监听器
//     */
//    void subscribe(final String topic, final String subExpression, final MessageListener listener);
//
//    /**
//     * 订阅消息，可以使用SQL表达式对消息进行过滤，请注意，SQL表达式过滤只针对MQ铂金版用户，公网服务暂时不支持。
//     *
//     * @param topic    消息主题
//     * @param listener 消息回调监听器
//     */
//    void subscribe(final String topic, final MessageListener listener);
//
//
//    /**
//     * 取消某个topic订阅
//     *
//     * @param topic 要取消的主题.
//     */
//    void unsubscribe(final String topic);


}
