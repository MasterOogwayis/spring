package com.zsw.orm.redis.utils;

import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.io.Closeable;
import java.util.Map;

/**
 * @author ZhangShaowei on 2021/3/30 9:26
 */
public class RedisHelper {

    public static <K, V> Cursor<K> scan(String pattern, long limit, RedisTemplate<K, V> redisTemplate) {
        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(limit).build();
        //noinspection unchecked
        return (Cursor<K>) redisTemplate.executeWithStickyConnection(
                (RedisCallback<Closeable>) redisConnection -> new ConvertingCursor<>(
                        redisConnection.scan(options),
                        redisTemplate.getKeySerializer()::deserialize)
        );
    }

    public static <K> Cursor<Map.Entry<Object, Object>> hScan(K key, String pattern, long limit, RedisTemplate<K, ?> redisTemplate) {
        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(limit).build();
        return redisTemplate.opsForHash().scan(key, options);
    }

    public static <K, V> Cursor<V> sScan(K key, String pattern, long limit, RedisTemplate<K, V> redisTemplate) {
        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(limit).build();
        return redisTemplate.opsForSet().scan(key, options);
    }

}
