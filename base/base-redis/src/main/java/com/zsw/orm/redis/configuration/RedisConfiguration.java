package com.zsw.orm.redis.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 启用SpringCache 使用RedisCacheManager做缓存控制器   RedisCacheConfiguration
 * <p>
 * 自定义控制时间的注解需要 开启同名bean 覆盖 spring.main.allow-bean-definition-overriding: true
 * 不然你得改全家桶代码
 *
 * @author ZhangShaowei on 2017/5/18 17:38
 */
@EnableCaching
@Configuration
//@EnableCustomCaching
public class RedisConfiguration {

    /**
     * 默认保存时间 30分钟
     */
    private static final Long DEFAULT_EXPIRATION = TimeUnit.MINUTES.toSeconds(30);


    /**
     * 这里注意不要被 IDE 给骗了，下面不带泛型会给警告!
     * 请无视泛型警告,不然其他地方你没办法注入 RedisTemplate<XX, XX>
     *
     * @param connectionFactory RedisConnectionFactory
     * @return RedisTemplate
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
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(om);
//        ProtostuffRedisSerializer serializer = new ProtostuffRedisSerializer();

        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 使用SpringCacheManager管理缓存   FIXME
     *
     * @return RedisCacheManager 配置
     * @see RedisCacheConfiguration
     */
    @Bean
    public CacheManager cacheManager(
            RedisConnectionFactory connectionFactory,
            ObjectProvider<CacheManagerCustomizers> customizersObjectProvider,
            ObjectProvider<CacheStrategy> strategyObjectProvider) {

//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(om);

        // 配置序列化策略
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration
                .defaultCacheConfig()
                // key 默认就是 stringSerializer
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
                // jackson 序列化 values
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
                .entryTtl(Duration.ofSeconds(DEFAULT_EXPIRATION))
                .computePrefixWith(name -> name + ":")
                .disableCachingNullValues();

        RedisCacheManager.RedisCacheManagerBuilder managerBuilder = RedisCacheManager
                .builder(RedisCacheWriter.lockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(defaultCacheConfig);
        // 自定义 key 的时间
        managerBuilder = strategyObjectProvider.stream()
                .map(CacheStrategy::expires)
                .filter(MapUtils::isNotEmpty)
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toMap(
                                        Map.Entry::getKey,
                                        entity -> defaultCacheConfig.entryTtl(Duration.ofSeconds(entity.getValue())),
                                        (k1, k2) -> k1
                                ),
                                // 最终都会执行到这一步 return this
                                managerBuilder::withInitialCacheConfigurations
                        )
                );
        CacheManager cacheManager = managerBuilder.build();
        customizersObjectProvider.ifAvailable(customizerInvoker -> customizerInvoker.customize(cacheManager));
        return cacheManager;
    }

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
}
