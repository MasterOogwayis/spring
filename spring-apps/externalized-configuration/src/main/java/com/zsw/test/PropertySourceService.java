package com.zsw.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShaowei on 2020/4/30 10:32
 */
@Component
@PropertySource("classpath:config.properties")
public class PropertySourceService {

    @Value("${user.name}")
    private String name;
    @Value("${user.address}")
    private String address;
    @Value("${user.age}")
    private String age;

    public Object user() {
        Map<String, String> user = new HashMap<>();
        user.put("name", name);
        user.put("address", address);
        user.put("age", age);
        return user;
    }

}
