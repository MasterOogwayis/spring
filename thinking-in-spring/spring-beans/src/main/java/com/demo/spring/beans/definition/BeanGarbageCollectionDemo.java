package com.demo.spring.beans.definition;

import com.demo.spring.beans.domain.InitDestroyBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * 如何垃圾回收 spring bean
 * <p>
 * 1. 关闭容器
 * 2. 执行 GC
 * 3. Spring Bean 覆盖 finalize() 方法
 *
 * @author ZhangShaowei on 2021/4/27 15:19
 */
@Slf4j
public class BeanGarbageCollectionDemo {


    public static void main(String[] args) throws Throwable {
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
