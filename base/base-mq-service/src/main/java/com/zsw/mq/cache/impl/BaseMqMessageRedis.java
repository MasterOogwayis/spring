package com.zsw.mq.cache.impl;

import com.zsw.base.redis.dao.RedisDao;
import com.zsw.mq.BaseMqMessage;
import com.zsw.mq.cache.BaseMqMessageCache;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 消息缓存
 *
 * @author ZhangShaowei on 2018/3/8 10:34
 **/
@Component
public class BaseMqMessageRedis extends RedisDao<String, BaseMqMessage> implements BaseMqMessageCache {


    /**
     * @param pattern
     * @return
     */
    @Override
    public Set<String> keys(final String pattern) {
        return this.redisTemplate.keys(pattern);
    }

}
