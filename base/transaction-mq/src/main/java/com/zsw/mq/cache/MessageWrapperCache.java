package com.zsw.mq.cache;


import com.zsw.orm.redis.dao.commons.CacheDao;

import java.util.Set;

/**
 * 消息缓存 - 定制的 消息缓存 带泛型
 *
 * @author ZhangShaowei on 2018/3/8 10:32
 **/
public interface MessageWrapperCache extends CacheDao<String, MessageWrapper> {

    /**
     * @param pattern keys *
     * @return
     */
    Set<String> keys(String pattern);

}
