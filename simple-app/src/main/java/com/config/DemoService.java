package com.config;

import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * @author ZhangShaowei on 2019/8/27 16:21
 **/
@AllArgsConstructor
public class DemoService {

    private Map<String, Long> map;

    public Map<String, Long> get() {
        return map;
    }


}
