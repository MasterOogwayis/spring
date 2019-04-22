package com.zsw.orm.http.apache;

import com.zsw.orm.http.RequestHttp;
import com.zsw.orm.http.SimplePostRequestExecutor;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Created by ecoolper on 2017/5/4.
 */
public class ApacheSimplePostRequestExecutor extends SimplePostRequestExecutor<CloseableHttpClient, HttpHost> {

    public ApacheSimplePostRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public String execute(String uri, String postEntity) throws Exception {
        HttpPost httpPost = new HttpPost(uri);
        if (requestHttp.getRequestHttpProxy() != null) {
            RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
            httpPost.setConfig(config);
        }

        if (postEntity != null) {
            StringEntity entity = new StringEntity(postEntity, Consts.UTF_8);
            httpPost.setEntity(entity);
        }

        try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
            String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
            if (responseContent.isEmpty()) {
                throw new Exception("无响应内容");
            }
            return responseContent;

        } finally {
            httpPost.releaseConnection();
        }
    }
}
