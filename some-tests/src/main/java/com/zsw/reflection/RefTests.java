package com.zsw.reflection;

import org.springframework.util.ReflectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShaowei on 2022/6/22 20:15
 */
public class RefTests {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();

        map.put("list", Collections.singletonList("a"));
        map.put("simple", "String");

        ReflectionUtils.doWithLocalFields(HashMap.class, field -> {
            System.out.println(field);
        });


    }

}
