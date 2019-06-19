package org.springframework.cache.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 增加了 过期时间的注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Cacheable
public @interface CustomCacheable {


    @AliasFor(value = "value", annotation = Cacheable.class)
    String[] value() default {};

    @AliasFor(value = "cacheNames", annotation = Cacheable.class)
    String[] cacheNames() default {};

    @AliasFor(value = "key", annotation = Cacheable.class)
    String key() default "";

    @AliasFor(value = "keyGenerator", annotation = Cacheable.class)
    String keyGenerator() default "";

    @AliasFor(value = "cacheManager", annotation = Cacheable.class)
    String cacheManager() default "";

    @AliasFor(value = "cacheResolver", annotation = Cacheable.class)
    String cacheResolver() default "";

    @AliasFor(value = "condition", annotation = Cacheable.class)
    String condition() default "";

    @AliasFor(value = "unless", annotation = Cacheable.class)
    String unless() default "";

    @AliasFor(value = "sync", annotation = Cacheable.class)
    boolean sync() default false;

    /**
     * 时间
     *
     * @return
     */
    long expire() default 30;


    /**
     *
     * 单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MINUTES;

}
