package com.zsw.mq.cache;


import com.zsw.base.redis.dao.commons.CacheDao;
import com.zsw.mq.BaseMqMessage;

import java.util.Set;

/**
 * 消息缓存 - 定制的 消息缓存 带泛型
 *
 * @author ZhangShaowei on 2018/3/8 10:32
 **/
public interface BaseMqMessageCache extends CacheDao<String, BaseMqMessage> {

    /**
     * @param pattern
     * @return
     */
    Set<String> keys(String pattern);

}
