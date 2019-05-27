package com.zsw.http.backup.httpclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zsw.http.backup.RequestHttp;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * 待实现
 *
 * @author ZhangShaowei on 2018/11/22 18:02
 **/
public class HttpClientRequest extends HttpClient implements RequestHttp {

    protected static final Gson gson = new GsonBuilder()
            .setLenient()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .disableHtmlEscaping()
            .create();

    @Override
    public String get(String uri) {
        return null;
    }

    @Override
    public <T> T get(String uri, Type clazz) {
        return null;
    }

    @Override
    public String get(String uri, Map<String, ?> params) {
        return null;
    }

    @Override
    public <T> T get(String uri, Map<String, ?> params, Type clazz) {
        return null;
    }

    @Override
    public String post(String uri, String body) {
        return null;
    }

    @Override
    public <T> T post(String uri, String data, Type clazz) {
        return null;
    }

    @Override
    public <T> T formSubmit(String uri, Map<String, ?> formData, Class<T> clazz) {
        return null;
    }
}
