package com.demo.spring.ioc.overview;

import com.demo.spring.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Shaowei Zhang on 2021/6/20 13:20
 */
@Component
@PropertySource("classpath:config/user.properties")
public class InjectingResourceDemo {

    /**
     * thinking-in-spring/ioc-container-overview/src/main/java/com/demo/spring/ioc/overview/InjectingResourceDemo.java
     */
    @Value("classpath:/com/demo/spring/ioc/overview/InjectingResourceDemo.java")
    private Resource resource;
    @Value("classpath:/config/user.properties")
    private Resource user;

    @Value("${name}")
    private String name;

    @Value("${user.dir}")
    private String dir;

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(InjectingResourceDemo.class);
        InjectingResourceDemo bean = applicationContext.getBean(InjectingResourceDemo.class);

        if (bean.resource.exists()) {
            System.out.println(ResourceUtils.getContext(bean.resource));
        }
        if (bean.user.exists()) {
            System.out.println(ResourceUtils.getContext(bean.user));
        }

        System.err.println(bean.name);
        System.err.println(bean.dir);


    }

}
