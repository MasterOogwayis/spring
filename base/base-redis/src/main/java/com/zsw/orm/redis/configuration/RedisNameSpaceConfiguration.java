package com.zsw.orm.redis.configuration;

import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2019/6/21 14:57
 **/

@Configuration
public class RedisNameSpaceConfiguration {


    /**
     * 配置 CacheNames，主要是控制缓存有效期和分层
     *
     * @return
     */
    @Bean
    public CacheStrategy cacheStrategy() {
        return RedisNameSpaceConfiguration.this::generateAllCacheNames;
    }

    /**
     * @return 所有 CacheConsts 实现类 enum
     */
    private Map<String, Long> generateAllCacheNames() {
        Reflections reflections = new Reflections(CacheConsts.class.getPackage().getName());
        Set<Class<? extends CacheConsts>> implEnums = reflections.getSubTypesOf(CacheConsts.class);
        return implEnums.stream().filter(Class::isEnum).flatMap(clazz -> {
            try {
                Method valuesMethod = clazz.getDeclaredMethod("values");
                return Stream.of((CacheConsts[]) valuesMethod.invoke(null, null));
            } catch (Exception e) {
                return null;
            }
        }).collect(Collectors.toMap(CacheConsts::getKey, CacheConsts::getExpire));
    }

}
