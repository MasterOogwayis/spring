package com.zsw.base.redis.serializer;


import io.protostuff.*;
import io.protostuff.runtime.RuntimeSchema;

import java.io.IOException;

/**
 * Protostuff序列化，线程安全，支持任意对象类型。
 * 使用对象图，支持循环引用、集合类属性等复杂对象。
 *
 * @author Ewing
 * @date 2017/6/15
 */
public class ProtostuffSerializer {

    private static Schema<ObjectWrapper> schema = RuntimeSchema.getSchema(ObjectWrapper.class);

//    private static CustomSchema<ObjectWrapper> customSchema = new CustomSchema<ObjectWrapper>(schemaBase) {
//        @Override
//        public void writeTo(Output output, ObjectWrapper message) throws IOException {
//            super.writeTo(output, message);
//        }
//    };


    /**
     * 对象序列化。
     *
     * @param object 对象实例。
     * @return 序列化字节。
     */
    public static byte[] serialize(Object object) {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
//        ProtostuffIOUtil.toByteArray(new ObjectWrapper(object), schema, buffer);
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
//        ProtostuffIOUtil.mergeFrom(bytes, objectWrapper, schema);
        return objectWrapper.getObject();
    }

}
