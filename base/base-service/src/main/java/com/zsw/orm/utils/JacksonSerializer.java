package com.zsw.orm.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
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
        //忽略没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //允许没有双引号
        mapper.getDeserializationConfig().with(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES);
        //允许转义字符
        mapper.getDeserializationConfig().with(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES);
        //允许单引号
        mapper.getDeserializationConfig().with(JsonReadFeature.ALLOW_SINGLE_QUOTES);
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
            final Class<? extends Collection<?>> collectionClass, final Class<?> elementClasses) {
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
     * @return json string
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
