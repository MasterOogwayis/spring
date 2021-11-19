package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author ZhangShaowei on 2021/11/18 9:39
 */
@EnableJpaRepositories
@EnableJpaAuditing(modifyOnCreate = false)
@SpringBootApplication
public class RepositoryStarter {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(RepositoryStarter.class, args);
    }

}
