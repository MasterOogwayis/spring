package com.zsw.utils;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author ZhangShaowei on 2019/6/4 14:02
 **/
public class JavaSerializer {
    @SneakyThrows
    public <T> byte[] serialize(T obj) {
        @Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        return baos.toByteArray();
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] data) {
        @Cleanup ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (T) ois.readObject();
    }

    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return deserialize(data);
    }
}
