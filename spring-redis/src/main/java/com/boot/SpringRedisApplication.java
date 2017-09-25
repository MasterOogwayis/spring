package com.boot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author ZhangShaowei on 2017/4/24 10:56
 */
@SpringBootApplication
@EntityScan(basePackages = "com.boot.persistence.bean")
@EnableJpaRepositories(basePackages = "com.boot.persistence.repository")
public class SpringRedisApplication {

    /**  */
    public static void main(String[] args) {
        SpringApplication.run(SpringRedisApplication.class, args);
    }

}
