package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2019/4/3 13:53
 **/
public class StaticTests {

    public static void main(String[] args) {
        List<Map<String, Integer>> list = new ArrayList<>();

        Map<String, Integer> map1 = new HashMap<>();
        map1.put("1", 1);
        map1.put("2", 2);
        map1.put("3", 3);
        map1.put("4", 4);
        map1.put("5", 5);
        map1.put("6", 6);

        list.add(map1);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("1", 1);
        map2.put("2", 2);
        map2.put("3", 3);
        map2.put("4", 4);
        map2.put("5", 5);
        map2.put("6", 6);

        list.add(map2);


        Stream<Map<String, Integer>> stream;

        if (list.size() < 2) {
            stream = list.stream();
        }else {
            stream = list.parallelStream();
        }

        stream.forEach(row -> {
            row.entrySet().forEach(entity -> {
                int value = entity.getValue();
                if (value % 2 == 0) {
                    entity.setValue(value * value);
                }
            });
        });


        System.out.println(list);


    }


    @Description("asd")
    @Role(1)
    @DependsOn("testApp")
    @Lazy()
    @Primary
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
    @Service(value = "product")
    class Product {

    }

}
