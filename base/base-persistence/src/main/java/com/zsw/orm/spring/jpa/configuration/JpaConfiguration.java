package com.zsw.orm.spring.jpa.configuration;

import com.zsw.orm.repository.impl.BaseRepositoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * durid 配置与 application.yml中 使用 Druid Spring Boot Starter
 *
 * @author ZhangShaowei on 2017/9/12 15:57
 */
@Configuration
@EnableJpaRepositories(basePackages = "com", repositoryBaseClass = BaseRepositoryImpl.class)
@EnableTransactionManagement
@EntityScan(basePackages = "com")
public class JpaConfiguration {


}
