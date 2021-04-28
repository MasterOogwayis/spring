package com.demo.spring.dependency.lookup;

import com.demo.spring.dependency.domain.ObjectHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ResolvableType;

import static com.zsw.utils.ExceptionHandler.handleException;

/**
 * @author ZhangShaowei on 2021/4/28 15:50
 */
@Slf4j
public class GenericLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan(ObjectHolder.class.getPackageName());
        applicationContext.refresh();

        ObjectProvider<ObjectHolder> beanProvider = applicationContext.getBeanProvider(ObjectHolder.class);
        beanProvider.stream().forEach(objectHolder -> {
            log.info("object: {}", objectHolder.getObject());
        });


        // 制定泛型类型的 bean
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(ObjectHolder.class, String.class);
        ObjectProvider<Object> provider = applicationContext.getBeanProvider(resolvableType);
        handleException(() -> {
            provider.stream().forEach(System.out::println);
        });


        applicationContext.close();

    }


}
