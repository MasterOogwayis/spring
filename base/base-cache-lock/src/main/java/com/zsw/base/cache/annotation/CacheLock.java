package com.zsw.base.cache.annotation;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 方法级的注解，用于注解会产生并发问题的方法
 *
 * @author ZhangShaowei on 2017/10/10 14:27
 */
@Target({METHOD, TYPE})
@Retention(RUNTIME)
@Inherited
@Documented
public @interface CacheLock {

    /**
     * Alias for {@link #cacheNames}.
     */
    @AliasFor("cacheNames")
    String[] value() default {};

    /**
     * Names of the caches in which method invocation results are stored.
     * <p>Names may be used to determine the target cache (or caches), matching
     * the qualifier value or bean name of a specific bean definition.
     * @since 4.2
     * @see #value
     * @see CacheConfig#cacheNames
     */
    @AliasFor("value")
    String[] cacheNames() default {};

    /**
     * Spring Expression Language (SpEL) expression for computing the key dynamically.
     * <p>Default is {@code ""}, meaning all method parameters are considered as a key,
     * unless a custom {@link #keyGenerator} has been configured.
     * <p>The SpEL expression evaluates against a dedicated context that provides the
     * following meta-data:
     * <ul>
     * <li>{@code #root.method}, {@code #root.target}, and {@code #root.caches} for
     * references to the {@link java.lang.reflect.Method method}, target object, and
     * affected cache(s) respectively.</li>
     * <li>Shortcuts for the method name ({@code #root.methodName}) and target class
     * ({@code #root.targetClass}) are also available.
     * <li>Method arguments can be accessed by index. For instance the second argument
     * can be accessed via {@code #root.args[1]}, {@code #p1} or {@code #a1}. Arguments
     * can also be accessed by name if that information is available.</li>
     * </ul>
     */
    String key() default "";

    /**
     * The bean name of the custom {@link org.springframework.cache.interceptor.KeyGenerator}
     * to use.
     * <p>Mutually exclusive with the {@link #key} attribute.
     * @see CacheConfig#keyGenerator
     */
    String keyGenerator() default "";

    /**
     * The bean name of the custom {@link org.springframework.cache.CacheManager} to use to
     * create a default {@link org.springframework.cache.interceptor.CacheResolver} if none
     * is set already.
     * <p>Mutually exclusive with the {@link #cacheResolver}  attribute.
     * @see org.springframework.cache.interceptor.SimpleCacheResolver
     * @see CacheConfig#cacheManager
     */
    String cacheManager() default "";

    /**
     * The bean name of the custom {@link org.springframework.cache.interceptor.CacheResolver}
     * to use.
     * @see CacheConfig#cacheResolver
     */
    String cacheResolver() default "";

    /**
     * Spring Expression Language (SpEL) expression used for making the method
     * caching conditional.
     * <p>Default is {@code ""}, meaning the method result is always cached.
     * <p>The SpEL expression evaluates against a dedicated context that provides the
     * following meta-data:
     * <ul>
     * <li>{@code #root.method}, {@code #root.target}, and {@code #root.caches} for
     * references to the {@link java.lang.reflect.Method method}, target object, and
     * affected cache(s) respectively.</li>
     * <li>Shortcuts for the method name ({@code #root.methodName}) and target class
     * ({@code #root.targetClass}) are also available.
     * <li>Method arguments can be accessed by index. For instance the second argument
     * can be accessed via {@code #root.args[1]}, {@code #p1} or {@code #a1}. Arguments
     * can also be accessed by name if that information is available.</li>
     * </ul>
     */
    String condition() default "";

    /**
     * @return 轮询锁的时间
     */
    long timeOut() default 2000L;

    /**
     * @return key在redis里存活的时间
     */
    long expireTime() default 1000L;

}
