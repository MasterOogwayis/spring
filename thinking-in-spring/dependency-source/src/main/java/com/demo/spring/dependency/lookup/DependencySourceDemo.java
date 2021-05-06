package com.demo.spring.dependency.lookup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * @author ZhangShaowei on 2021/5/6 11:04
 */
@Slf4j
public class DependencySourceDemo {


    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext ac;

    @Autowired
    private ApplicationEventPublisher publisher;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);
        applicationContext.refresh();

        log.info("getBean BeanFactory: {}", applicationContext.getBeansOfType(BeanFactory.class));
        log.info("getBean ResourceLoader: {}", applicationContext.getBeansOfType(ResourceLoader.class));
        log.info("getBean ApplicationContext: {}", applicationContext.getBeansOfType(ApplicationContext.class));
        log.info("getBean ApplicationEventPublisher: {}", applicationContext.getBeansOfType(ApplicationEventPublisher.class));

        applicationContext.close();



    }


    @PostConstruct
    public void init() {
        log.info("Autowired BeanFactory: {}", beanFactory);
        log.info("Autowired ResourceLoader: {}", resourceLoader);
        log.info("Autowired ApplicationContext: {}", ac);
        log.info("Autowired ApplicationEventPublisher: {}", publisher);
    }

}
