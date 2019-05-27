package com.zsw.http.backup.okhttp;

import com.google.gson.*;
import com.zsw.http.backup.RequestHttp;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 使用 okhttp 请求客户端
 *
 * @author ZhangShaowei on 2018/11/20 15:02
 **/
@Slf4j
public class OkHttpRequest implements RequestHttp {

    public static void main(String[] args) {
        String s = new FormPoster("https://ddpt.e1le-cloud.com/invoice/v1").callForString();
        System.out.println(s);
    }

    protected static final Gson gson = new GsonBuilder()
            .setLenient()
            .enableComplexMapKeySerialization()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .disableHtmlEscaping()
            .create();

    /**
     * http  客户端
     */
    public static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    private static final MediaType STREAM = MediaType.parse("application/octet-stream");
    private static final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    /**
     * 请求器，链式调用
     *
     * @param <R>
     */
    public abstract static class Requester<R extends Requester> {

        protected Request.Builder builder = new Request.Builder();

        public R header(String name, String value) {
            builder.header(name, value);
            return (R) this;
        }

        /**
         * get/post 添加参数方法不同
         *
         * @param name
         * @param value
         * @return
         */
        public abstract R param(String name, Object value);

        /**
         * 将 map 添加到参数
         *
         * @param map
         * @return
         */
        public R map(Map<String, ?> map) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                param(entry.getKey(), entry.getValue());
            }
            return (R) this;
        }

        protected abstract Request buildRequest();

        public <T> T callForObject(Type type) {
            return OkHttpRequest.callForObject(buildRequest(), type);
        }

        public String callForString() {
            return OkHttpRequest.callForString(buildRequest());
        }

        public void callback(Callback callback) {
            OkHttpRequest.callback(buildRequest(), callback);
        }
    }

    /**
     * Get请求器。
     */
    public static class Getter extends Requester<Getter> {
        protected StringBuilder urlBuilder;
        private boolean hasParam;

        public Getter(String url) {
            this.urlBuilder = new StringBuilder(url);
            this.hasParam = url.contains("?");
        }

        @Override
        public Getter param(String name, Object value) {
            if (hasParam) {
                urlBuilder.append('&');
            } else {
                urlBuilder.append('?');
                hasParam = true;
            }
            urlBuilder.append(encodeUrl(name)).append('=').append(encodeUrl(value));
            return this;
        }

        @Override
        protected Request buildRequest() {
            return builder.get().url(urlBuilder.toString()).build();
        }
    }

    /**
     * Form 请求器。
     */
    public static class FormPoster extends Requester<FormPoster> {
        protected FormBody.Builder formBuilder = new FormBody.Builder();

        public FormPoster(String url) {
            this.builder.url(url);
        }

        @Override
        public FormPoster param(String name, Object value) {
            formBuilder.add(String.valueOf(name), String.valueOf(value));
//            formBuilder.addEncoded(name, String.valueOf(value));
            return this;
        }

        @Override
        protected Request buildRequest() {
            return builder.post(formBuilder.build()).build();
        }
    }


    /**
     * Body的Post请求器。
     */
    public static class BodyPoster extends Requester<BodyPoster> {
        protected JsonElement jsonBody = JsonNull.INSTANCE;
        protected MediaType contentType = null;

        public BodyPoster(String url) {
            this.builder.url(url);
        }

        /**
         *
         */
        public BodyPoster contentType(String contentType) {
            this.contentType = MediaType.parse(contentType);
            return this;
        }

        public BodyPoster json(String json) {
            this.jsonBody = gson.fromJson(json, JsonElement.class);
            return this;
        }

        public BodyPoster gson(JsonElement json) {
            this.jsonBody = json == null ? JsonNull.INSTANCE : json;
            return this;
        }

        @Override
        public BodyPoster param(String name, Object value) {
            if (jsonBody.isJsonNull()) {
                jsonBody = new JsonObject();
            } else if (!jsonBody.isJsonObject()) {
                throw new RuntimeException("Only JsonObject can add param.");
            }
            String nameStr = String.valueOf(name);
            JsonObject jsonObject = (JsonObject) jsonBody;
            if (value == null) {
                jsonObject.add(nameStr, JsonNull.INSTANCE);
            } else if (value instanceof Number) {
                jsonObject.addProperty(nameStr, (Number) value);
            } else if (value instanceof Boolean) {
                jsonObject.addProperty(nameStr, (Boolean) value);
            } else if (value instanceof Character) {
                jsonObject.addProperty(nameStr, (Character) value);
            } else if (value instanceof String) {
                jsonObject.addProperty(nameStr, (String) value);
            } else {
                jsonObject.add(nameStr, gson.toJsonTree(value));
            }
            return this;
        }

        public BodyPoster add(Object value) {
            if (jsonBody.isJsonNull()) {
                jsonBody = new JsonArray();
            } else if (!jsonBody.isJsonArray()) {
                throw new RuntimeException("Only JsonArray can add element.");
            }
            JsonArray jsonArray = (JsonArray) jsonBody;
            if (value == null) {
                jsonArray.add(JsonNull.INSTANCE);
            } else if (value instanceof Number) {
                jsonArray.add((Number) value);
            } else if (value instanceof Boolean) {
                jsonArray.add((Boolean) value);
            } else if (value instanceof Character) {
                jsonArray.add((Character) value);
            } else if (value instanceof String) {
                jsonArray.add((String) value);
            } else {
                jsonArray.add(gson.toJsonTree(value));
            }
            return this;
        }

        @Override
        protected Request buildRequest() {
            return builder.post(RequestBody.create(Objects.nonNull(this.contentType) ? contentType : JSON, jsonBody.toString())).build();
        }
    }


    /**
     * 执行Request请求并返回String值。
     */
    private static String callForString(Request request) {
        try {
            log.debug("callForString:url:{}", request.url().url().toString());
            Response response = CLIENT.newCall(request).execute();
            String result = response.body().string();
            log.debug("callForString:result:{}", result == null ? null : result.length() > 250 ?
                    (result.substring(0, 200) + "..." + result.substring(result.length() - 50)) : result);
            response.close(); // 内部静默关闭
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Request IOException.", e);
        }
    }


    /**
     * 执行Request请求并将返回的Json转成对象。
     */
    private static <T> T callForObject(Request request, Type type) {
        String result = OkHttpRequest.callForString(request);
        return gson.fromJson(result, type);
    }

    /**
     * 执行Request请求并在完成时执行回调方法。
     */
    private static void callback(Request request, Callback callback) {
        CLIENT.newCall(request).enqueue(callback);
    }


    /**
     * 使用UTF-8进行URL参数编码。
     */
    private static String encodeUrl(Object source) {
        try {
            return URLEncoder.encode(String.valueOf(source), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String get(String uri) {
        return new Getter(uri).callForString();
    }

    @Override
    public <T> T get(String uri, Type clazz) {
        return new Getter(uri).callForObject(clazz);
    }

    @Override
    public String get(String uri, Map<String, ?> params) {
        return new Getter(uri).map(params).callForString();
    }

    @Override
    public <T> T get(String uri, Map<String, ?> params, Type clazz) {
        return new Getter(uri).map(params).callForObject(clazz);
    }

    @Override
    public <T> T post(String uri, String data, Type clazz) {
        return new BodyPoster(uri).json(data).callForObject(clazz);
    }

    @Override
    public String post(String uri, String body) {
        return new BodyPoster(uri).json(body).callForString();
    }

    @Override
    public <T> T formSubmit(String uri, Map<String, ?> formData, Class<T> clazz) {
        return new FormPoster(uri)
                .map(formData)
                .callForObject(clazz);
    }


}
