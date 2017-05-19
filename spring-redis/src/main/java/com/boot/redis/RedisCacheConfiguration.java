package com.boot.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author ZhangShaowei on 2017/5/19 13:54
 */
@Configuration
@EnableCaching
public class RedisCacheConfiguration extends CachingConfigurerSupport {

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

        // spring data redis项目中有一个#145 pull request
        // 而这个提交请求的内容正是解决Jackson必须提供类型信息的问题
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(om);

        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
//        String[] cacheNames = {"app_default", "users", "blogs", "goods", "configs", "info"};
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
        redisCacheManager.setDefaultExpiration(1800L);
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


}
