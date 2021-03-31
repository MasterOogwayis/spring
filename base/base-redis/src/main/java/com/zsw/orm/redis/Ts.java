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
import java.util.Map;
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

    @GetMapping("scan")
    public Set<String> scan(@RequestParam("pattern") String pattern, @RequestParam("limit") Long limit) {
        Set<String> set = new HashSet<>();
        try (Cursor<String> cursor = RedisHelper.scan(pattern, limit, redisTemplate)) {
            cursor.forEachRemaining(set::add);
            log.error("set size = {}", set.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    @GetMapping("hScan")
    public Set<Map.Entry<Object, Object>> hScan(
            @RequestParam("key") String key,
            @RequestParam("pattern") String pattern,
            @RequestParam("limit") Long limit) {
        Set<Map.Entry<Object, Object>> set = new HashSet<>();
        try (Cursor<Map.Entry<Object, Object>> cursor = RedisHelper.hScan(key, pattern, limit, redisTemplate)) {
            cursor.forEachRemaining(set::add);
            log.error("set size = {}", set.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    @GetMapping("sScan")
    public Set<Integer> sScan(
            @RequestParam("key") String key,
            @RequestParam(value = "pattern", required = false) String pattern,
            @RequestParam("limit") Long limit) {
        Set<Integer> set = new HashSet<>();
        try (Cursor<Integer> cursor = RedisHelper.sScan(key, pattern, limit, redisTemplate)) {
            cursor.forEachRemaining(set::add);
            log.error("set size = {}", set.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

}
