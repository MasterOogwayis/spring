package com;

import com.zsw.orm.redis.dao.commons.BaseCacheDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2019/4/26 10:46
 **/
@RequestMapping("cache")
@RestController
public class SimpleController {

    @Autowired
    BaseCacheDao cache;


    @GetMapping("pttl")
    public Object pttl(@RequestParam("key") String key) {
        return this.cache.pttl(key, TimeUnit.SECONDS);
    }




}
