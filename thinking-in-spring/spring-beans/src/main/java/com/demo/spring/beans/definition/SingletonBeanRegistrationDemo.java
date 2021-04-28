package com.demo.spring.beans.definition;

import com.demo.spring.beans.domain.InitDestroyBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Objects;

/**
 * @author ZhangShaowei on 2021/4/27 15:53
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        InitDestroyBean bean = new InitDestroyBean();

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("singleton", bean);
        applicationContext.refresh();

        InitDestroyBean lookupBean = applicationContext.getBean("singleton", InitDestroyBean.class);

        System.out.println(Objects.equals(lookupBean, bean));


        applicationContext.close();

    }

}
