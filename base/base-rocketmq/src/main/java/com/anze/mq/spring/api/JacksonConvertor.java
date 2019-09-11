package com.anze.mq.spring.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.Charset;

/**
 * @author ZhangShaowei on 2019/9/11 17:07
 **/
public class JacksonConvertor implements DataConvertor {

    private static final Charset UTF8 = Charset.forName("utf-8");

    private ObjectMapper objectMapper;

    @Override
    public byte[] convert(Object payloadObj) {
        byte[] payloads;

        if (payloadObj instanceof String) {
            payloads = ((String) payloadObj).getBytes(UTF8);
        } else if (payloadObj instanceof byte[]) {
            payloads = (byte[]) payloadObj;
        } else {
            try {
                String jsonObj = objectMapper.writeValueAsString(payloadObj);
                payloads = jsonObj.getBytes(UTF8);
            } catch (Exception e) {
                throw new RuntimeException("convert to RocketMQ message failed.", e);
            }
        }
        return payloads;
    }
}
