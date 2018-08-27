package com.zsw.mq.base;

public class BaseMqMessage {

    protected String topic;
    protected String tags;
    protected String keys;
    //分区顺序消息中区分不同分区的关键字段，sharding key于普通消息的key是完全不同的概念。
    //全局顺序消息，该字段可以设置为任意非空字符串。
    protected String shardingKey;
    protected Object arg;
    private String producerId;
    private String body;


    public String getShardingKey() {
        return shardingKey;
    }

    public void setShardingKey(String shardingKey) {
        this.shardingKey = shardingKey;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public Object getArg() {
        return arg;
    }

    public void setArg(Object arg) {
        this.arg = arg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }
}
