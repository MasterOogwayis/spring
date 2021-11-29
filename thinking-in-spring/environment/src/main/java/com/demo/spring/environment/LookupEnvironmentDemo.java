package com.demo.spring.environment;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Objects;

import static org.springframework.context.ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME;

/**
 * @author ZhangShaowei on 2021/11/29 10:24
 */
@Slf4j
public class LookupEnvironmentDemo implements EnvironmentAware {

    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LookupEnvironmentDemo.class);
        applicationContext.refresh();

        var bean = applicationContext.getBean(LookupEnvironmentDemo.class);
        Environment environment1 = applicationContext.getBean(ENVIRONMENT_BEAN_NAME, Environment.class);

        System.out.println(Objects.equals(bean.environment, environment1));

        applicationContext.close();

    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
