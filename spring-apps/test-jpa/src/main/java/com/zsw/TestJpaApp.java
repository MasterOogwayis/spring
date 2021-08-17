package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ZhangShaowei on 2020/9/25 13:58
 */
@EntityScan
//@EnableJpaAuditing
@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication(proxyBeanMethods = false)
public class TestJpaApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(TestJpaApp.class, args);
    }

}
