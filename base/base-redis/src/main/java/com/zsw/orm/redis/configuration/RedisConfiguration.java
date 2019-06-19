package com.zsw.orm.redis.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CustomCachingConfigurationSelector;
import org.springframework.cache.annotation.CustomProxyCachingConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.annotation.ProxyCachingConfiguration;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 启用SpringCache 使用RedisCacheManager做缓存控制器   RedisCacheConfiguration
 *
 * 自定义控制时间的注解需要 开启同名bean 覆盖 spring.main.allow-bean-definition-overriding: true
 * 不然你得改全家桶代码
 *
 * @author ZhangShaowei on 2017/5/18 17:38
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "zsw.base.redis.configuration")
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ProxyCachingConfiguration.class))
@Import(CustomProxyCachingConfiguration.class)
@EnableCaching
@Validated
public class RedisConfiguration {

    /**
     * CacheName - ttl(seconds)
     */
    private Map<String, Long> expires;

    /**
     * 默认保存时间 30分钟
     */
    private Long defaultExpiration = 60 * 30L;


    /**
     * @return
     */
    @Bean
    @SuppressWarnings("unchecked")
    public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);

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
        //     *** 可读性略差, 底层为 Unsafe 反射拷贝！所以遇见 hibernate 代理对象就要炸！
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
     * @see org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     */
    @Bean
    public CacheManager cacheManager(
            RedisConnectionFactory connectionFactory,
            @Nullable CacheManagerCustomizers customizerInvoker) {

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration
                .defaultCacheConfig()
                // 默认配置， 默认超时时间为30min
                .entryTtl(Duration.ofSeconds(TimeUnit.MINUTES.toSeconds(30)))
                .computePrefixWith(name -> name + ":")
                .disableCachingNullValues();
        RedisCacheManager.RedisCacheManagerBuilder managerBuilder = RedisCacheManager
                .builder(RedisCacheWriter.lockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(defaultCacheConfig);
        if (!CollectionUtils.isEmpty(this.expires)) {
            Map<String, RedisCacheConfiguration> cacheConfigurations = new LinkedHashMap<>();
            expires.forEach((key, value) -> {
                cacheConfigurations.put(key, defaultCacheConfig.entryTtl(Duration.ofSeconds(value)));
            });
            managerBuilder.withInitialCacheConfigurations(cacheConfigurations);
        }
        if (Objects.isNull(customizerInvoker)) {
            return managerBuilder.build();
        }
        return customizerInvoker.customize(managerBuilder.build());
    }

//    @Bean
//    public CacheManagerCustomizer<RedisCacheManager> cacheManagerCustomizer() {
//        return cacheManager -> {
//            // 设置默认 key 有效期
//            cacheManager.setDefaultExpiration(defaultExpiration);
//            // 设置指定队列 key 有效期
//            if (!CollectionUtils.isEmpty(this.expires)) {
//                cacheManager.setExpires(this.expires);
//            }
//        };
//    }

//    @Bean
//    public CacheManagerCustomizer<RedisCacheManager> cacheManagerCustomizer() {
//        return new CacheManagerCustomizer<RedisCacheManager>() {
//            @Override
//            public void customize(RedisCacheManager cacheManager) {
//                // 设置默认 key 有效期
//                cacheManager.setDefaultExpiration(defaultExpiration);
//                // 设置指定队列 key 有效期
//                if (!CollectionUtils.isEmpty(this.expires)) {
//                    cacheManager.setExpires(this.expires);
//                }
//            }
//        };
//    }

//    /**
//     * @return
//     */
//    @Bean
//    public Cache cache(CacheManager cacheManager) {
//        return cacheManager.getCache("app_default");
//    }

    /**
     * 缺省key
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName()).append(":").append(method.getName()).append(":");
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
     *
     */
    public Map<String, Long> getExpires() {
        return expires;
    }

    /**
     *
     */
    public Long getDefaultExpiration() {
        return defaultExpiration;
    }
}
