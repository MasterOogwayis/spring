package com.zsw.base.redis.service;

import com.zsw.base.redis.dao.commons.BaseCacheDao;
import com.zsw.base.redis.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2017/9/12 15:22
 */

public class RedisService {

    /**
     * base cache
     */
    @Autowired
    private BaseCacheDao cache;


    /**
     * 描述： 验证用户访问频率 -- 令牌桶算法  <br>
     * 将用户每次访问的时间戳放入一个固定长度(单位时间允许访问最大次数)的队列<br>
     * 当队列长度大于等于 最大长度,比较队首元素和当前时间戳时差<br>
     * 大于单位时间则通过，移除队首元素并将当前时间戳放入队尾<br>
     * 小于单位时间则禁止通行
     * 队列设置生存周期，超出单位时间则自动销毁
     * 每次访问 都更新当前队列的生存时间
     *
     * @param args 偏移量customerCode methodName ClassName等  能指定唯一key
     * @return true or false
     * @author : zhangshaowei
     * @since : v1.0
     */
    public Boolean accessFrequency(final String... args) {
        Boolean access = true;
        String key = RedisKeyUtils.getKey(args);
        //现在访问的时间戳
        Long now = System.currentTimeMillis();
        Long accessTimes = 10L;
        Long accessTimeUnit = 60L * 1000L;
        if (this.cache.size(key) >= accessTimes) {
            Long first = (Long) this.cache.get(key, 0L);
            access = now - first >= accessTimeUnit;
            if (access) { //时间间隔大于允许范围 移除第一个 并将当前时间戳插入到队尾
                this.cache.leftPop(key);
                this.cache.rightPush(key, now);
            }
        } else { //访问次数未达到指定次数则不验证 ，仅将当前时间戳放入队尾
            this.cache.rightPush(key, now);
        }
        //每次访问都刷新该队列的生存时间
        this.cache.expire(key, accessTimeUnit, TimeUnit.MILLISECONDS);
        return access;
    }


}
