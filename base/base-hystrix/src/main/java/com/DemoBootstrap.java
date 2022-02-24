package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Administrator on 2019/9/22 16:57
 **/
//@EnableCircuitBreaker
@EnableAspectJAutoProxy
@SpringBootApplication
public class DemoBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(DemoBootstrap.class, args);
    }

}
