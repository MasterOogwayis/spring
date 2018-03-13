package com.zsw.mq;

public class BaseMqMessage {

    protected String topic;

    protected String tags;

    protected String keys;

    protected Object arg;

    private String body;

    private String producerId;

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
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

    @Override
    public String toString() {
        return "BaseMqMessage{" +
                "topic='" + topic + '\'' +
                ", tags='" + tags + '\'' +
                ", keys='" + keys + '\'' +
                ", arg=" + arg +
                ", body='" + body + '\'' +
                ", producerId='" + producerId + '\'' +
                '}';
    }
}
