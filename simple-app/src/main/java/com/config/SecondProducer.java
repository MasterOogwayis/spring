package com.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShaowei on 2019/8/27 16:22
 **/
//@Component
public class SecondProducer implements Producer {
    @Override
    public Map<String, Long> get() {
        Map<String, Long> map = new HashMap<>();
        map.put("4", 4L);
        map.put("5", 5L);
        map.put("6", 6L);
        map.put("7", 7L);
        return map;
    }
}
