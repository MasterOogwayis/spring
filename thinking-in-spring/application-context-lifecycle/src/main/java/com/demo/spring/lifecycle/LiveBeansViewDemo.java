package com.demo.spring.lifecycle;

import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.LiveBeansView;

import static org.springframework.context.support.LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME;

/**
 * @author ZhangShaowei on 2021/12/15 9:39
 */
public class LiveBeansViewDemo {


    /**
     * @param args
     * @see LiveBeansView
     */
    @SneakyThrows
    public static void main(String[] args) {

        //  添加 LiveBeansView 的 ObjectName 的 domain
        System.setProperty(MBEAN_DOMAIN_PROPERTY_NAME, LiveBeansViewDemo.class.getPackageName());

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LiveBeansViewDemo.class);
        applicationContext.refresh();


        System.out.println("...");

        int read = System.in.read();

        applicationContext.close();


    }

}
