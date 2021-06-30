package com.zsw.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * @author ZhangShaowei on 2021/6/30 16:45
 */
final public class JacksonUtils {

    static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    @SneakyThrows
    public static String toJson(Object obj) {
        return mapper.writeValueAsString(obj);
    }

    @SneakyThrows
    public static <T> T readValue(String json, Class<T> clazz) {
        return mapper.readValue(json, clazz);
    }

}
