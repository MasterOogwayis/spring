package com.zsw.base.redis.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.NotNull;
import com.zsw.base.redis.serializer.ProtostuffRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 启用SpringCache 使用RedisCacheManager做缓存控制器
 *
 * @author ZhangShaowei on 2017/5/18 17:38
 */
@Configuration
@ConfigurationProperties(prefix = "zsw.base.redis.configuration")
@EnableCaching
@Validated
public class RedisConfiguration extends CachingConfigurerSupport {

    /**
     *
     */
    @NotNull
    private Map<String, Long> expires;

    /**
     * 默认保存时间 30分钟
     */
    private Long defaultExpiration = 1800L;

    /**
     * 缓存名称
     */
    private List<String> cacheNames;

    /**
     * 这是默认的key队列
     */
    private static final String BASE_QUEUE = "redis";

    /**
     *
     */
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    /**
     * @return
     */
    @Bean
    @SuppressWarnings("unchecked")
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);

        // key:
        // 所有key使用字符串类型
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);

        // value
        //
        //  1. 默认 JdkSerializationRedisSerializer - JDK提供的序列化功能
        //      优点是反序列化时不需要提供类型信息(class)，但缺点是序列化后的结果非常庞大，
        //      是JSON格式的5倍左右，这样就会消耗redis服务器的大量内存
        //      速度仅仅比json稍快,所以优先考虑json序列化

        //  2. Jackson2JsonRedisSerializer 必须提供类型信息
        //      使用Jackson库将对象序列化为JSON字符串。优点是速度快，序列化后的字符串短小精悍。
        //      但缺点也非常致命，那就是此类的构造函数中有一个类型参数，必须提供要序列化对象的类型信息(.class对象)
        //      通过查看源代码，发现其只在反序列化过程中用到了类型信息
        //
        //  3. GenericJackson2JsonRedisSerializer 解决 2 不需要传类型参数
        //
        //  4. protostuff  比Jackson性能好，空间少的方式，value为二进制方式  --  jpa的代理对象无法被序列化二进制
        //     *** 可读性略差
        //     *** 无法序列化 jpa 代理对象handler
        //     由google提供
        //
        //
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(om);
//        ProtostuffRedisSerializer serializer = new ProtostuffRedisSerializer();

        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 使用SpringCacheManager管理缓存
     *
     * @return RedisCacheManager 配置
     */
    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
//        redisCacheManager.setTransactionAware(false);操作缓存不开启事物 or 不能及时得到缓存数据反馈
        if (!CollectionUtils.isEmpty(this.expires)) {
            redisCacheManager.setExpires(this.expires);
            redisCacheManager.setCacheNames(this.expires.keySet());
        } else {
            redisCacheManager.setCacheNames(
                    CollectionUtils.isEmpty(
                            this.cacheNames) ? Collections.singletonList(BASE_QUEUE) : this.cacheNames);
            redisCacheManager.setDefaultExpiration(this.defaultExpiration);
        }
        return redisCacheManager;

    }

    /**
     * @return
     */
    @Bean
    public Cache cache() {
        return cacheManager().getCache("app_default");
    }

    /**
     * 缺省key
     *
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName()).append("::").append(method.getName()).append(":");
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }


    /**
     * @param expires expires
     */
    public void setExpires(final Map<String, Long> expires) {
        this.expires = expires;
    }


    /**
     * @param defaultExpiration defaultExpiration
     */
    public void setDefaultExpiration(final Long defaultExpiration) {
        this.defaultExpiration = defaultExpiration;
    }

    /**
     * @param cacheNames cacheNames
     */
    public void setCacheNames(final List<String> cacheNames) {
        this.cacheNames = cacheNames;
    }

    /**  */
    public Map<String, Long> getExpires() {
        return expires;
    }

    /**  */
    public Long getDefaultExpiration() {
        return defaultExpiration;
    }

    /**  */
    public List<String> getCacheNames() {
        return cacheNames;
    }
}
