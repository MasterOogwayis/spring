package com.zsw.example.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2020/8/31 11:48
 */
@Getter
@Setter
@Service
@RefreshScope
public class ConfigService {

    @Value("${config.name}")
    private String name;

    public String sayHello(String name) {
        return "Hello " + name;
    }


}
