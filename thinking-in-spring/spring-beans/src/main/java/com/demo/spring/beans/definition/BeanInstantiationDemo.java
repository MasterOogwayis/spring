package com.demo.spring.beans.definition;

import com.demo.spring.beans.domain.InitDestroyBean;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Bean 的初始化
 *
 * @author ZhangShaowei on 2021/4/27 10:04
 */
@Slf4j
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        test();
    }


    @SneakyThrows
    public static void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(InitDestroyBean.class)
                .setInitMethodName("init2")
                .setDestroyMethodName("destroy2");
//                .setLazyInit(Boolean.TRUE);

        applicationContext.registerBeanDefinition("initBean", builder.getBeanDefinition());
        applicationContext.refresh();


        var bean = applicationContext.getBean(InitDestroyBean.class);
        log.info("initBean: {}", bean);

        applicationContext.close();

        bean = null;
        System.gc();

        TimeUnit.SECONDS.sleep(5);


    }


}
