package com.zsw.orm.redis.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.io.Closeable;
import java.util.function.Consumer;

/**
 * @author ZhangShaowei on 2021/3/30 9:26
 */
public class RedisHelper {


    public static <K, V> Cursor<K> scan(RedisTemplate<K, V> redisTemplate, String pattern, int limit) {
        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(limit).build();
        //noinspection unchecked
        return (Cursor<K>) redisTemplate.executeWithStickyConnection(
                (RedisCallback<Closeable>) redisConnection -> new ConvertingCursor<>(
                        redisConnection.scan(options),
                        redisTemplate.getKeySerializer()::deserialize)
        );

    }

}
