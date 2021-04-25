package com.demo.spring.ioc.overview.dependency.injection;

import com.demo.spring.ioc.overview.component.BeanFactoryHolder;
import com.demo.spring.ioc.overview.component.UserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ZhangShaowei on 2021/4/23 16:04
 */
@Slf4j
public class DependencyInjectionDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext("com.demo");

        BeanFactory beanFactory = applicationContext;
        BeanFactoryHolder holder = applicationContext.getBean(BeanFactoryHolder.class);

        System.out.println(beanFactory == holder.getBeanFactory());
        System.err.println(applicationContext.getBeanFactory() == holder.getBeanFactory());
        // BeanFactory 这些都属于内建的对象，getBean 是拿不到的，
        // 但是依赖注入就可以注入。所以依赖注入和依赖查找还是有些许区别的
//        System.out.println(applicationContext.getBean(BeanFactory.class));

        UserProperties userProperties = applicationContext.getBean(UserProperties.class);

        log.info("userProperties = {}", userProperties);
    }

}
