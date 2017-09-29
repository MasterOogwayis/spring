package com.zsw.base.redis.serializer;

import com.dyuproject.protostuff.GraphIOUtil;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 自定义的 序列化工具，主体为google提供的protostuff
 *
 * @author ZhangShaowei on 2017/9/13 10:27
 */

public class ProtostuffRedisSerializer implements RedisSerializer<Object> {

    /** */
    private final Schema<ObjectWrapper> schema = RuntimeSchema.getSchema(ObjectWrapper.class);

    /**
     * 序列化
     *
     * @return byte[]
     * @throws SerializationException e
     */
    @Override
    public byte[] serialize(final Object object) {
        if (object == null) {
            return null;
        }

        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

        try {
            return GraphIOUtil.toByteArray(new ObjectWrapper(object), schema, buffer);
//            return ProtostuffIOUtil.toByteArray(new ObjectWrapper(object), schema, buffer);
        } finally {
            buffer.clear();
        }
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
        if (ObjectUtils.isEmpty(bytes)) {
            return null;
        }
        try {
            ObjectWrapper objectWrapper = new ObjectWrapper();
            ProtostuffIOUtil.mergeFrom(bytes, objectWrapper, schema);
            return objectWrapper.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
