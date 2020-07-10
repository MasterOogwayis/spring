package com.zsw.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * durid 配置与 application.yml中 使用 Druid Spring Boot Starter
 *
 * @author ZhangShaowei on 2017/9/12 15:57
 */
@Configuration
@EnableJpaRepositories(basePackages = "com", repositoryBaseClass = SimpleJpaRepository.class)
@EnableTransactionManagement
@EntityScan(basePackages = "com")
public class JpaConfiguration {


}
