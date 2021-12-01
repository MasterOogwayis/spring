package com.demo.spring.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZhangShaowei on 2021/11/26 13:30
 */
@Slf4j
@TestPropertySource(
        properties = "user.name = zsw",
        locations = "classpath:META-INF/test.properties"
)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EnvironmentPropertySourceChangeTests.class)
public class EnvironmentPropertySourceChangeTests {

    @Value("${user.name}")
    private String name;

    @Autowired
    private ConfigurableEnvironment environment;

    @Test
    public void test() {
        log.info("name: {}", name);

        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            log.info("PropertySource={}, name={}", propertySource.getName(), propertySource.getProperty("user.name"));
        }

    }

}
