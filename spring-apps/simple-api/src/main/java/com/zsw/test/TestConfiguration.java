package com.zsw.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShaowei on 2020/7/13 16:36
 */

@Configuration
@EnableConfigurationProperties(TestProperties.class)
public class TestConfiguration implements InitializingBean {

    @Autowired
    private TestProperties properties;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(properties);
    }
}
