package com.zsw.orm.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Collection;

/**
 * @author ZhangShaowei on 2017/05/09 17:54
 */

public class JacksonSerializer {

    /**
     * jackson的json转换器
     */
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * 初始化mapper参数
     */
    public JacksonSerializer() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //忽略没有的属性
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); //允许没有双引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true); //允许转义字符
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true); //允许单引号
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(new EnumSerializer());
//
//        mapper.registerModule(simpleModule);
    }

    /**
     * jackson 获取集合对象类型
     *
     * @param collectionClass List ...
     * @param elementClasses  Object.class
     * @return JavaType
     */
    protected JavaType getCollectionType(
            final Class<? extends Collection> collectionClass, final Class<?> elementClasses) {
        return this.mapper.getTypeFactory().constructCollectionType(collectionClass, elementClasses);

    }

    /**
     * @return ObjectMapper
     */
    public ObjectMapper getMapper() {
        return mapper;
    }

    /**
     * @param object data
     * @return
     */
    protected String toJson(Object object) {
        try {
            return this.mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    @SneakyThrows
    public static void main(String[] args) {
        JacksonSerializer serializable = new JacksonSerializer();
        String s = serializable.getMapper().writeValueAsString(TypeE.HUMAN);
        System.out.println(s);
    }

}
