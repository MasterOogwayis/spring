package com.zsw.mq;

/**
 * This is base mq service.
 *
 * @author ZhangShaowei on 2018/3/12 10:30
 **/
public interface BaseMqService {

    /**
     * 发送简单消息
     *
     * @param message BaseMqMessage
     */
    void sendSimpleMessage(BaseMqMessage message);

}
