package com.zsw.lesson.serializer.protostuff;


import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import sun.reflect.misc.ReflectUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Protostuff序列化，线程安全，支持任意对象类型。
 * 使用对象图，支持循环引用、集合类属性等复杂对象。
 *
 * @author Ewing
 * @date 2017/6/15
 */
public class ProtostuffSerializerBackup {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

    private static <T> Schema<T> getSchema(Class<T> clazz) {
        //noinspection unchecked
        return (Schema<T>) cachedSchema.getOrDefault(clazz, RuntimeSchema.getSchema(clazz));
    }

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
    public static <T> byte[] serialize(T object) {
        //noinspection unchecked
        Class<T> clazz = (Class<T>) object.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
        return ProtostuffIOUtil.toByteArray(object, getSchema(clazz), buffer);
//            return GraphIOUtil.toByteArray(new ObjectWrapper(object), schema, buffer);
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
//    public static Object deserialize(byte[] bytes) {
//        ObjectWrapper objectWrapper = schema.newMessage();
//        GraphIOUtil.mergeFrom(bytes, objectWrapper, schema);
////        ProtostuffIOUtil.mergeFrom(bytes, objectWrapper, schema);
//        return objectWrapper.getObject();
//    }

    public static <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try {
            T instance = (T) ReflectUtil.newInstance(clazz);
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(bytes, instance, schema);
            return instance;
        } catch (Exception e) {
            return null;
        }
    }

}
