package com.zsw.orm.http.backup;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * 使用 Type 替代 Class 以便复杂类型的转换
 *
 * @author ZhangShaowei on 2018/11/20 14:56
 **/
public interface RequestHttp {

    String get(String uri);

    String get(String uri, Map<String, ?> params);

    <T> T get(String uri, Type clazz);

    <T> T get(String uri, Map<String, ?> params, Type clazz);

    String post(String uri, String body);

    <T> T post(String uri, String data, Type clazz);
}
