package org;

import org.springframework.cache.annotation.CustomCacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 嘿嘿嘿 on 2019/6/19 14:44
 **/
@Service
public class DemoService {

    @CustomCacheable(value = "db", key = "'demo:' + #key", expire = 1, timeUnit = TimeUnit.HOURS)
    public Object get(String key) {
        return "value:" + key;
    }

}
