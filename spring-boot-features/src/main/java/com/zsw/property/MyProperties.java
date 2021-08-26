package com.zsw.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author ZhangShaowei on 2021/8/19 16:30
 */
@Getter
@Setter
@Component
@ConfigurationProperties("config")
@Validated
public class MyProperties {

    private Map<String, String> map;

//    @NotNull
    private String name;

    private String profiles;

    private String db;

}
