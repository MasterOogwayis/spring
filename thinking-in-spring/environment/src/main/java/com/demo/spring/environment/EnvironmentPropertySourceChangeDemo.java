package com.demo.spring.environment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;

/**
 * @author ZhangShaowei on 2021/11/26 13:30
 */
@Slf4j
public class EnvironmentPropertySourceChangeDemo {

    @Value("${user.name}")
    private String name;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EnvironmentPropertySourceChangeDemo.class);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        HashMap<String, Object> source = new HashMap<>();
        source.put("user.name", "zsw");
        propertySources.addFirst(new MapPropertySource("first-property-source-zsw", source));

        applicationContext.refresh();

        source.put("user.name", "007");

        EnvironmentPropertySourceChangeDemo main = applicationContext.getBean(EnvironmentPropertySourceChangeDemo.class);
        // 不会更新
        System.out.println(main.name);

        propertySources.forEach(s -> {
            log.info("PropertySource(name={}), user.name = {}", s.getName(), s.getProperty("user.name") );
        });


        applicationContext.close();

    }

}
