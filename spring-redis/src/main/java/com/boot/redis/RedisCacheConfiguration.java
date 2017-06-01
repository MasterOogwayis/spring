package com.boot.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangShaowei on 2017/5/19 13:54
 */
@ConfigurationProperties(prefix = "anze.base.redis")
@Configuration
@EnableCaching
public class RedisCacheConfiguration extends CachingConfigurerSupport {

    /**
     * 默认cacheNames
     */
    @Value("${spring.application.name}")
    private String serverName;

    /**
     *
     */
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
     *
     */
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    /**
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);

        //value：
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
        //  3. GenericJackson2JsonRedisSerializer
        //
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(om);

        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * @return RedisCacheManager
     */
    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
        if (!CollectionUtils.isEmpty(this.expires)) {
            redisCacheManager.setExpires(this.expires);
            redisCacheManager.setCacheNames(this.expires.keySet());
        } else {
            redisCacheManager.setCacheNames(
                    CollectionUtils.isEmpty(
                            this.cacheNames) ? Arrays.asList(this.serverName) : this.cacheNames);
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
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append("::" + method.getName() + ":");
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }


    /**  */
    public Map<String, Long> getExpires() {
        return expires;
    }

    /**  */
    public void setExpires(Map<String, Long> expires) {
        this.expires = expires;
    }

    /**  */
    public Long getDefaultExpiration() {
        return defaultExpiration;
    }

    /**  */
    public void setDefaultExpiration(Long defaultExpiration) {
        this.defaultExpiration = defaultExpiration;
    }

    /**  */
    public List<String> getCacheNames() {
        return cacheNames;
    }

    /**  */
    public void setCacheNames(List<String> cacheNames) {
        this.cacheNames = cacheNames;
    }
}
