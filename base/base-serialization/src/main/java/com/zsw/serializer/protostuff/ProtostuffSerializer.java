package com.zsw.serializer.protostuff;


import com.zsw.lesson.serializer.protostuff.ObjectWrapper;
import io.protostuff.GraphIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Collections;
import java.util.Map;

/**
 * Protostuff序列化，线程安全，支持任意对象类型。
 * 使用对象图，支持循环引用、集合类属性等复杂对象。
 *
 * @author zsw
 * @date 2017/6/15
 */
public class ProtostuffSerializer {

    private static Schema<ObjectWrapper> schema = RuntimeSchema.getSchema(ObjectWrapper.class);


    /**
     * 对象序列化。
     *
     * @param object 对象实例。
     * @return 序列化字节。
     */
    public static byte[] serialize(Object object) {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return GraphIOUtil.toByteArray(new ObjectWrapper(object), schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 对象反序列化。
     *
     * @param bytes 序列化字节。
     * @return 对象实例。
     */
    @SuppressWarnings("unchecked")
    public static Object deserialize(byte[] bytes) {
        ObjectWrapper objectWrapper = schema.newMessage();
        GraphIOUtil.mergeFrom(bytes, objectWrapper, schema);
        return objectWrapper.getObject();
    }


    public static void main(String[] args) {
        Map<String, Object> person = Collections.singletonMap("name", "zsw");

        byte[] serialize = serialize(person);

        Map<String, ?> deserialize = (Map<String, ?>) deserialize(serialize);

        System.out.println(deserialize);


    }

}
