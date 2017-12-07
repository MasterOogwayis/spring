package com.zsw.base.redis.annotation;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 缓存锁 字段参考 CacheEvict
 *
 * 使用CacheEvict 保证锁在方法执行完后被删除
 *
 * @author ZhangShaowei on 2017/12/5 17:11
 */
@CacheEvict
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheLock {

    /**
     * Alias for {@link #cacheNames}.
     */
    @AliasFor(annotation = CacheEvict.class)
    String[] value() default {};

    /**
     * Names of the caches in which method invocation results are stored.
     * <p>Names may be used to determine the target cache (or caches), matching
     * the qualifier value or bean name of a specific bean definition.
     *
     * @see #value
     * @see CacheConfig#cacheNames
     * @since 4.2
     */
    @AliasFor(annotation = CacheEvict.class)
    String[] cacheNames() default {};

    /**
     * Spring Expression Language (SpEL) expression for computing the key dynamically.
     */
    @AliasFor(annotation = CacheEvict.class, attribute = "key")
    String key() default "";

    /**
     * The bean name of the custom {@link org.springframework.cache.interceptor.KeyGenerator}
     * to use.
     * <p>Mutually exclusive with the {@link #key} attribute.
     *
     * @see CacheConfig#keyGenerator
     */
    @AliasFor(annotation = CacheEvict.class)
    String keyGenerator() default "";

    /**
     * Spring Expression Language (SpEL) expression used for making the cache
     * eviction operation conditional.
     * <p>Default is {@code ""}, meaning the cache eviction is always performed.
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
    @AliasFor(annotation = CacheEvict.class)
    String condition() default "";

}
