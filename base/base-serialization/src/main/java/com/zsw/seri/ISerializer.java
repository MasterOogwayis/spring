package com.zsw.seri;

public interface ISerializer {

    <T> byte[] serialize(T obj);


    <T> T deserialize(byte[] data);


    <T> T deserialize(byte[] data, Class<T> clazz);
}
