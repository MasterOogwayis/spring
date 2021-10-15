package com.demo.beanorder;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ZhangShaowei on 2021/10/15 14:51
 */
public class TestBeanInitOrder {


    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan(TestBeanInitOrder.class.getPackageName());
        applicationContext.refresh();



        D d = applicationContext.getBean(D.class);
        System.out.println(d);

        applicationContext.close();

    }

}
