package com.zsw.mq.cache;



import com.zsw.mq.base.BaseMqMessage;

import java.io.Serializable;

/**
 * Message Wrapper 缓存消息 主体 包含key + message
 *
 * @author ZhangShaowei on 2018/3/14 15:21
 **/
public class MessageWrapper implements Serializable {
    private static final long serialVersionUID = 8480070236804423204L;

    /**
     * 消息地址
     */
    private String key;

    /**
     * 消息 body
     */
    private BaseMqMessage message;

    /**
     * 计数器 消息发送次数
     */
    private int counter;


    /**  */
    public String getKey() {
        return key;
    }

    /**  */
    public void setKey(String key) {
        this.key = key;
    }

    /**  */
    public BaseMqMessage getMessage() {
        return message;
    }

    /**  */
    public void setMessage(BaseMqMessage message) {
        this.message = message;
    }

    /**  */
    public int getCounter() {
        return counter;
    }

    /**  */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    public MessageWrapper(String key, BaseMqMessage message) {
        this.key = key;
        this.message = message;
        this.counter = 0;
    }

    public MessageWrapper() {
    }

}
