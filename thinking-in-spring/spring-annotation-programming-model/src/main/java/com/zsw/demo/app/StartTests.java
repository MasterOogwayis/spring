package com.zsw.demo.app;

import com.zsw.demo.annotation.MyComponentScan;
import com.zsw.demo.component1.A;
import com.zsw.demo.component2.B;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ZhangShaowei on 2021/11/10 10:03
 */
@MyComponentScan(clazz = A.class, scan = "com.zsw.demo.component2")
@SpringBootApplication
public class StartTests {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder()
                .sources(StartTests.class)
                .web(WebApplicationType.NONE)
                .run(args);

        ObjectProvider<Parent> provider = applicationContext.getBeanProvider(Parent.class);
        provider.stream().map(Parent::hello).forEach(System.out::println);


    }

}
