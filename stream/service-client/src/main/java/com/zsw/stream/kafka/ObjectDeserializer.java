package com.zsw.stream.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * @author ZhangShaowei on 2019/9/23 14:57
 **/
@Slf4j
public class ObjectDeserializer implements Deserializer<Serializable> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        log.info("configs={}", configs);
    }

    @Override
    public Serializable deserialize(String topic, byte[] data) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            return (Serializable) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
