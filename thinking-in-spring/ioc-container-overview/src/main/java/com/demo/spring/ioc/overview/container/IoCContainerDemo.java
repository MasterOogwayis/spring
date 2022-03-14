package com.demo.spring.ioc.overview.container;

import com.demo.spring.ioc.overview.domain.RpcClientFactoryBean;
import com.demo.spring.ioc.overview.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * IoC 容器示例
 *
 * @author ZhangShaowei on 2021/4/25 11:29
 */
@Slf4j
class IoCContainerDemo {

    public static void main(String[] args) {
//        testXml();
        testAnnotation();

    }

    public static void testAnnotation() {
        // 1. 创建容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 2. 将当前类作为 ConfigurationClass
        applicationContext.register(IoCContainerDemo.class);
        // 3. 启动应用上下文
        applicationContext.refresh();
        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
        beansOfType.forEach((k, v) -> {
            log.info("name = {}, value = {}", k, v);
        });
        applicationContext.registerBean("name", RpcClientFactoryBean.class);

        String name = applicationContext.getBean(String.class);
        System.err.println(name);

        // 4. 停止
//        applicationContext.stop();
        applicationContext.close();
    }


    public static void testXml() {
        // 1. 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String path = "classpath:META-INF/dependency-lookup-context.xml";
        int i = reader.loadBeanDefinitions(path);
        User user = beanFactory.getBean(User.class);
        log.info("number = {}, bean = {}", i, user);
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(2L);
        user.setName("Demo");
        return user;
    }

}
