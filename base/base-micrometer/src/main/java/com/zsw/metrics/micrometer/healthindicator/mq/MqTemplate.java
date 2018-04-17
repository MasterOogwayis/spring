package com.zsw.metrics.micrometer.healthindicator.mq;

/**
 * Mq Template 自定义mq检查接口
 *
 * @author ZhangShaowei on 2018/4/12 10:07
 **/
public interface MqTemplate {

    /**
     * 获取 mq 版本号 进行健康检查
     *
     * @return mq version
     * @throws Exception e
     */
    String getVersion() throws Exception;

}
