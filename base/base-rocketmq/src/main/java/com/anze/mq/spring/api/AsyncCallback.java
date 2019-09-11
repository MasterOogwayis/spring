package com.anze.mq.spring.api;

/**
 * @author ZhangShaowei on 2019/9/11 16:31
 **/
public interface AsyncCallback {

    /**成功
     *
     * @param sendResult
     */
    void success(SendResult sendResult);

    /**
     * 失败
     *
     * @param throwable
     */
    void onError(Throwable throwable);

}
