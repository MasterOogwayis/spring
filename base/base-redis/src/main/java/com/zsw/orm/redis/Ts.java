package com.zsw.orm.redis;

import com.zsw.orm.redis.utils.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2021/3/30 9:44
 */
@Slf4j
@RequestMapping
@RestController
public class Ts {

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    @GetMapping("flush")
    public Object flush() {
        return redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.flushDb();
            return "success";
        });
    }

    @GetMapping("init")
    public Object init(@RequestParam("size") Integer size) {
        String prefix = "test:";

        Map<String, Integer> map = new HashMap<>(size * 2);
        for (int i = 0; i < size; i++) {
            map.put(prefix + "nums:" + i, i);
        }

        // kvs
        this.redisTemplate.opsForValue().multiSet(map);

        // hash
        this.redisTemplate.opsForHash().putAll(prefix + "map", map);

        // set
        map.values().forEach(v -> this.redisTemplate.opsForSet().add(prefix + "set", v));

        // zset
        map.values().stream()
                .map(v -> (ZSetOperations.TypedTuple<Integer>) new DefaultTypedTuple<Integer>(v, v.doubleValue()))
                .collect(Collectors.collectingAndThen(
                        Collectors.toSet(),
                        set -> this.redisTemplate.opsForZSet().add(prefix+"zset", set)
                ));
        return "success";

    }


    @GetMapping("scan")
    public Set<String> scan(@RequestParam("pattern") String pattern, @RequestParam("limit") Long limit) {
        Set<String> set = new HashSet<>();
        RedisHelper.scan(pattern, limit, redisTemplate, set::add);
        return set;
    }

    @GetMapping("hScan")
    public Set<Map.Entry<Object, Object>> hScan(
            @RequestParam("key") String key,
            @RequestParam("pattern") String pattern,
            @RequestParam("limit") Long limit) {
        Set<Map.Entry<Object, Object>> set = new HashSet<>();
        RedisHelper.hScan(key, pattern, limit, redisTemplate, set::add);
        return set;
    }

    @GetMapping("sScan")
    public Set<Integer> sScan(
            @RequestParam("key") String key,
            @RequestParam(value = "pattern", required = false) String pattern,
            @RequestParam("limit") Long limit) {
        Set<Integer> set = new HashSet<>();
        RedisHelper.sScan(key, pattern, limit, redisTemplate, set::add);
        return set;
    }

}
