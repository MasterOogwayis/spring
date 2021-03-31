package com.zsw.orm.redis.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;

/**
 * redis 辅助，用来替代各种使用 keys、members 等有风险的命令
 * Cursor 属于重资源，使用后必须关闭。
 * 不过规则定的再多也总有人不会遵守，所以只有被动防御。几个方法都依赖这种模式，不提供 Cursor
 *
 * @author ZhangShaowei on 2021/3/30 9:26
 */
@Slf4j
public class RedisHelper {

    /**
     * 扫描匹配的 key
     *
     * @param pattern       redis key 匹配规则 ep: key:*
     * @param limit         cursor 分片大小
     * @param redisTemplate RedisTemplate
     * @param consumer      如何处理遍历得到的每个key
     * @param <K>           K
     */
    public static <K> void scan(
            String pattern, long limit, RedisTemplate<K, ?> redisTemplate, Consumer<K> consumer) {
        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(limit).build();
        //noinspection unchecked
        try (
                Cursor<K> cursor = (Cursor<K>) redisTemplate.executeWithStickyConnection(
                        (RedisCallback<Closeable>) redisConnection -> new ConvertingCursor<>(
                                redisConnection.scan(options),
                                redisTemplate.getKeySerializer()::deserialize)
                )
        ) {
            cursor.forEachRemaining(consumer);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("redis scan error", e);
        }
    }

    public static <K> void hScan(
            K key, String pattern, long limit,
            RedisTemplate<K, ?> redisTemplate, Consumer<Map.Entry<Object, Object>> consumer) {
        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(limit).build();
        try (Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(key, options)) {
            cursor.forEachRemaining(consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <K, V> void sScan(
            K key, String pattern, long limit, RedisTemplate<K, V> redisTemplate, Consumer<V> consumer) {
        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(limit).build();
        try (Cursor<V> cursor = redisTemplate.opsForSet().scan(key, options)) {
            cursor.forEachRemaining(consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <K, V> void zScan(
            K key, String pattern, long limit, RedisTemplate<K, V> redisTemplate, Consumer<V> consumer) {
        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(limit).build();
        try (Cursor<ZSetOperations.TypedTuple<V>> cursor = redisTemplate.opsForZSet().scan(key, options)) {
            cursor.forEachRemaining(tt -> consumer.accept(tt.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
