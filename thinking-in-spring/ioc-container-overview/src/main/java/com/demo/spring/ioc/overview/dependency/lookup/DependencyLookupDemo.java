package com.demo.spring.ioc.overview.dependency.lookup;

import com.demo.spring.ioc.overview.domain.User;
import com.demo.spring.ioc.overview.annotation.Super;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * 依赖查找事例
 * 1. 通过名称的方式来查找
 *
 * @author ZhangShaowei on 2021/4/23 14:52
 */
@Slf4j
public class DependencyLookupDemo {

    public static void main(String[] args) {
        new DependencyLookupDemo().test();
    }

    public void test() {
//        ClassPathXmlApplicationContext applicationContext
//                = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.demo");

        User user = (User) applicationContext.getBean("user");
        log.info("user = {}", user);
        BeanFactory beanFactory = applicationContext.getBeanFactory();

        lookupInLazy(beanFactory);

        lookupInRealTime(beanFactory);

        user = lookupByType(beanFactory, User.class);

        applicationContext.registerBean("userTwo", User.class, () -> {
            User u = new User();
            u.setId(Long.MAX_VALUE);
            u.setName("This is a test");
            return u;
        });

        Map<String, User> map = lookupCollectionByType(beanFactory, User.class);

        map.forEach((k, v) -> {
            log.info("name = {}, value = {}", k, v);
        });

        lookupByAnnotationtype(beanFactory);
    }

    private void lookupByAnnotationtype(BeanFactory beanFactory) {
        Map<String, Object> beans = ((ListableBeanFactory) beanFactory).getBeansWithAnnotation(Super.class);
        beans.forEach((k, v) -> {
            log.info("lookupByAnnotationtype: name = {}, value = {}", k, v);
        });
    }

    private <T> Map<String, T> lookupCollectionByType(BeanFactory beanFactory, Class<T> clazz) {

        ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
        return listableBeanFactory.getBeansOfType(clazz);
    }

    private <T> T lookupByType(BeanFactory beanFactory, Class<T> clazz) {
        return beanFactory.getBean(clazz);
    }


    /**
     * 延时查找
     *
     * @param beanFactory
     */
    public <T> T lookupInLazy(BeanFactory beanFactory) {
        return null;
    }

    /**
     * 及时查找
     *
     * @param beanFactory
     */
    public <T> T lookupInRealTime(BeanFactory beanFactory) {
        return null;
    }

}
