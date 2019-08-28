package com.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShaowei on 2019/8/27 16:22
 **/
//@Component
public class FirstProducer implements Producer {
    @Override
    public Map<String, Long> get() {
        Map<String, Long> map = new HashMap<>();
        map.put("1", 1L);
        map.put("2", 2L);
        map.put("3", 3L);
        map.put("4", 4L);
        return map;
    }
}
