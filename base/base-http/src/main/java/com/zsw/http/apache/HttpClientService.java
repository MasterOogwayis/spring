package com.zsw.http.apache;

import com.zsw.http.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * 不支持代理
 *
 * @author ZhangShaowei on 2018/11/15 16:42
 **/
@Getter
@Setter
public class HttpClientService implements HttpService, RequestHttp<CloseableHttpClient, HttpHost> {

    private CloseableHttpClient httpClient;

    public HttpClientService() {
        this(DefaultApacheHttpClientBuilder.get().build());
    }

    public HttpClientService(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    @Override
    public String get(String url, String queryParam) throws Exception {
        return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
    }

    @Override
    public String post(String url, String postData) throws Exception {
        return execute(SimplePostRequestExecutor.create(this), url, postData);
    }

    private  <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws Exception {
        return executor.execute(uri, data);
    }

    @Override
    public CloseableHttpClient getRequestHttpClient() {
        return this.httpClient;
    }

    @Override
    public HttpHost getRequestHttpProxy() {
        return null;
    }

    @Override
    public HttpType getRequestType() {
        return HttpType.APACHE_HTTP;
    }
}
