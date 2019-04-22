package com.zsw.orm.redis.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * 自定义的 序列化工具，主体为google提供的protostuff
 *
 * @author ZhangShaowei on 2017/9/13 10:27
 */

public class ProtostuffRedisSerializer implements RedisSerializer<Object> {

    /**
     * 序列化
     *
     * @return byte[]
     * @throws SerializationException e
     */
    @Override
    public byte[] serialize(final Object value) {
        return ProtostuffSerializer.serialize(value);
    }

    /**
     * 反序列化
     *
     * @param bytes 数据字节
     * @return Object
     * @throws SerializationException e
     */
    @Override
    public Object deserialize(final byte[] bytes) {
        return ProtostuffSerializer.deserialize(bytes);
    }

}
