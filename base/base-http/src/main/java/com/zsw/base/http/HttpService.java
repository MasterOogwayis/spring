package com.zsw.base.http;

/**
 * @author ZhangShaowei on 2018/11/15 16:58
 **/
public interface HttpService {

    String get(String url, String queryParam) throws Exception;

    String post(String url, String postData) throws Exception;

}
