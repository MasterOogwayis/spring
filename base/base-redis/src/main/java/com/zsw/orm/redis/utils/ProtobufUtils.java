//package com.zsw.orm.redis.utils;
//
//import com.google.protobuf.GeneratedMessageV3;
//import com.google.protobuf.GeneratedMessageV3.Builder;
//import com.google.protobuf.InvalidProtocolBufferException;
//import com.google.protobuf.MessageOrBuilder;
//
//import java.lang.reflect.Method;
//
///**
// * @author Administrator on 2019/6/2 21:33
// **/
//public class ProtobufUtils {
//    public static boolean isSupported(Class<?> clazz) {
//        if (clazz == null) {
//            return false;
//        }
//
//        if (GeneratedMessageV3.class.isAssignableFrom(clazz)) {
//            return true;
//        }
//        return false;
//    }
//
//    public static <T> T deserialize(String json, Class<T> requestClass) throws InvalidProtocolBufferException {
//        Builder builder;
//        try {
//            builder = getMessageBuilder(requestClass);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Get google protobuf message builder from " + requestClass.getName() + "failed", e);
//        }
//        JsonFormat.parser().merge(json, builder);
//        return (T) builder.build();
//    }
//
//    public static String serialize(Object value) throws InvalidProtocolBufferException {
//        JsonFormat.Printer printer = JsonFormat.printer().omittingInsignificantWhitespace();
//        return printer.print((MessageOrBuilder) value);
//    }
//
//    private static Builder getMessageBuilder(Class<?> requestType) throws Exception {
//        Method method = requestType.getMethod("newBuilder");
//        return (Builder) method.invoke(null, null);
//    }

//}
