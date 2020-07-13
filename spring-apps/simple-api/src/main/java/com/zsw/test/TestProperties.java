package com.zsw.test;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ZhangShaowei on 2020/7/13 16:37
 */
@Data
@ConfigurationProperties(prefix = "com.zsw")
public class TestProperties {

    /**
     * 姓名
     */
    private String name;

}
