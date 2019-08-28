package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2019/8/27 16:21
 **/
@Slf4j
@Configuration
public class DemoConfiguration {

    @Bean
    public DemoService demoService(ObjectProvider<Producer> objectProvider) {
        Map<String, Long> collect = new HashMap<>();
        collect.put("key", 1L);
        collect = objectProvider.stream()
                .map(Producer::get)
                .filter(map -> !CollectionUtils.isEmpty(map))
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k1, k2) -> k1),
                        map -> {
                            System.err.println(123);
                            return map;
                        }
                ));

        return new DemoService(collect);
    }


}
