package com.zsw.seri;

import io.protostuff.GraphIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * @author ZhangShaowei on 2019/6/4 14:37
 **/
public class ProtostuffSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        LinkedBuffer buffer = LinkedBuffer.allocate();
        Schema schema = RuntimeSchema.getSchema(obj.getClass());
        return GraphIOUtil.toByteArray(obj, schema, buffer);
    }

    @Override
    public <T> T deserialize(byte[] data) {
        throw new IllegalArgumentException("miss class");
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {

        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        T result = schema.newMessage();
        GraphIOUtil.mergeFrom(data, result, schema);
        return result;
    }
}
