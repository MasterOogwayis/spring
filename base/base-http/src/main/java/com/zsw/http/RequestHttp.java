package com.zsw.http;

/**
 * Created by zsw on 2017/4/22.
 */
public interface RequestHttp<H, P> {

    /**
     * 返回httpClient
     */
    H getRequestHttpClient();

    /**
     * 返回httpProxy
     */
    P getRequestHttpProxy();

    HttpType getRequestType();

}
