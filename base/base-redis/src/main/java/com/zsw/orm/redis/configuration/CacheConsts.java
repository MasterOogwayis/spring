package com.zsw.orm.redis.configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2019/6/20 11:12
 **/
public interface CacheConsts {

    long DEFAULT_EXPIRE = TimeUnit.MINUTES.toSeconds(30);


    /**
     * 缓存 key name space
     *
     * @return
     */
    String getKey();

    /**
     * 缓存时间  单位 秒 s
     *
     * @return
     */
    Long getExpire();


//    CacheConsts[] values();

}
