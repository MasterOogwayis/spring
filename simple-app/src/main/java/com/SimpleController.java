package com;

import com.zsw.orm.redis.dao.commons.BaseCacheDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2019/4/26 10:46
 **/
@Slf4j
@RequestMapping("get")
@RestController
public class SimpleController {

    @Autowired
    private BaseCacheDao cacheDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    SimpleService simpleService;

    @GetMapping("get")
    public Object get(@RequestParam("name") String name) {
        return this.simpleService.get(name);
    }


    @GetMapping("mget")
    public Object get() throws InterruptedException {
        Set<String> keys = new HashSet<>();
        keys.add("name1");
        keys.add("name2");
        keys.add("name3");
        keys.add("name4");

//        Set<byte[]> collect = keys.stream().map(key -> redisTemplate.getKeySerializer().serialize(keys)).collect(Collectors.toSet());
//
//        this.redisTemplate.executePipelined(new RedisCallback<Object>() {
//
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                return connection.mGet(collect.toArray())
//            }
//        }, this.redisTemplate.getValueSerializer());


        return cacheDao.multiGet(keys);
    }

}
