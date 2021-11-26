package com.demo.spring.environment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/4/25 10:37
 */
@ToString
@Getter
@Setter
@Component
@PropertySource("classpath:config/user.properties")
public class UserProperties {

    @Value("${name}")
    private String name;

    @Value("${age}")
    private Integer age;

    @Value("${address}")
    private String address;

}
