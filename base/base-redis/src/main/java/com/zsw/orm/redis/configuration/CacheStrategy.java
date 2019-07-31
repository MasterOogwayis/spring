package com.zsw.orm.redis.configuration;

import java.util.Map;

/**
 * @author ZhangShaowei on 2019/7/29 12:16
 **/
public interface CacheStrategy {

    /**
     * 配置缓存
     *
     * @return
     */
    Map<String, Long> expires();

}
