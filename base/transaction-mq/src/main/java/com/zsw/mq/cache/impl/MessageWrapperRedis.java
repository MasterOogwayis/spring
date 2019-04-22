package com.zsw.mq.cache.impl;

import com.zsw.orm.redis.dao.RedisDao;
import com.zsw.mq.cache.MessageWrapper;
import com.zsw.mq.cache.MessageWrapperCache;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 消息缓存
 *
 * @author ZhangShaowei on 2018/3/8 10:34
 **/
@Component
public class MessageWrapperRedis extends RedisDao<String, MessageWrapper> implements MessageWrapperCache {


    /**
     * @param pattern
     * @return
     */
    @Override
    public Set<String> keys(final String pattern) {
        return this.redisTemplate.keys(pattern);
    }

}
