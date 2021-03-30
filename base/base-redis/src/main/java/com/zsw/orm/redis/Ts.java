package com.zsw.orm.redis;

import com.zsw.orm.redis.utils.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ZhangShaowei on 2021/3/30 9:44
 */
@Slf4j
@RequestMapping
@RestController
public class Ts {

    private final RedisTemplate<String, Integer> redisTemplate;

    public Ts(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("size")
    public Set<String> keys(@RequestParam("limit") Integer limit) {
        String key = "test:kvs:nums";
        int size = 10000;
//        Map<String, Integer> map = new HashMap<>();
//        for (int i = 0; i < size; i++) {
//            map.put(key + ":" + i, i);
//        }
//        redisTemplate.opsForValue().multiSet(map);

//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        Future<Integer> submit = executor.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return null;
//            }
//        });
        Set<String> set = new HashSet<>();
        try (Cursor<String> scan = RedisHelper.scan(redisTemplate, key + ":*", limit)) {
            scan.forEachRemaining(set::add);
            log.error("set size = {}", set.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return set;
    }

}
