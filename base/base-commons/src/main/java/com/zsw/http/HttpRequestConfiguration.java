package com.zsw.http;

import com.zsw.http.okhttp.OkHttpRequest;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShaowei on 2018/12/27 18:24
 **/
@Configuration
@ConditionalOnClass(OkHttpClient.class)
public class HttpRequestConfiguration {

    /**
     * 提供一个全局可用的 http 访问工具
     *
     * @return
     */
    @Bean
    public RequestHttp requestHttp() {
        return new OkHttpRequest();
    }

}
