package com.demo.spring.environment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author ZhangShaowei on 2021/11/29 10:12
 */
@Slf4j
public class InjectingEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    private Environment environment;
    @Autowired
    private Environment environment1;

    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationContext applicationContext1;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectingEnvironmentDemo.class);

        applicationContext.refresh();

        InjectingEnvironmentDemo bean = applicationContext.getBean(InjectingEnvironmentDemo.class);

        log.info("environment: {}", bean.environment);
        log.info("environment1: {}", bean.environment1);
        log.info("applicationContext: {}", bean.applicationContext);
        log.info("applicationContext1: {}", bean.applicationContext1);

        applicationContext.close();

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
