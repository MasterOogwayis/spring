package com.zsw.mq.spring.autoconfigure;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * @author ZhangShaowei on 2019/9/11 17:07
 **/
@Slf4j
@AllArgsConstructor
public class JacksonMessageSerializer implements MessageSerializer {

    private static final byte[] EMPTY_ARRAY = new byte[0];

    private static final Charset UTF8 = Charset.forName("utf-8");

    private ObjectMapper objectMapper;

    @Override
    public byte[] serialize(Object payloadObj) {
        if (Objects.isNull(payloadObj)) {
            return EMPTY_ARRAY;
        }
        byte[] payloads;

        if (payloadObj instanceof String) {
            payloads = ((String) payloadObj).getBytes(UTF8);
        } else if (payloadObj instanceof byte[]) {
            payloads = (byte[]) payloadObj;
        } else {
            try {
                String jsonObj = this.objectMapper.writeValueAsString(payloadObj);
                payloads = jsonObj.getBytes(UTF8);
            } catch (Exception e) {
                throw new RuntimeException("serialize to RocketMQ message failed.", e);
            }
        }
        return payloads;
    }

    @Override
    public <T> T deserialize(byte[] body, Class<T> clazz) throws IOException {
        if (ArrayUtils.isEmpty(body)) {
            return null;
        }
        String str = new String(body, UTF8);
        if (Objects.equals(clazz, String.class)) {
            //noinspection unchecked
            return (T) str;
        } else {
            // If msgType not string, use objectMapper change it.
            try {
                return this.objectMapper.readValue(str, clazz);
            } catch (Exception e) {
                log.info("serialize failed. str:{}, msgType:{}", str, clazz);
                throw new RuntimeException("cannot serialize message to " + clazz, e);
            }
        }
    }
}
