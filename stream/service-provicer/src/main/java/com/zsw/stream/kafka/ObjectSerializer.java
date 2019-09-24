package com.zsw.stream.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author ZhangShaowei on 2019/9/23 14:57
 **/
@Slf4j
public class ObjectSerializer implements Serializer<Serializable> {
    @Override
    public byte[] serialize(String topic, Serializable data) {
        log.info("topic={}, data={}", topic, data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(data);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
