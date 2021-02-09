package com.zsw.client;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ZhangShaowei on 2021/2/9 10:34
 */
@Component
public class MessageClientHystrix implements MessageClient {
    @Override
    public String send(Map<String, Object> map) {
        return "fallback";
    }
    @Override
    public String send1(Map<String, Object> map) {
        return "fallback send1";
    }
}
