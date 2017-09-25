package com.zsw.base.spring.jpa.configuration;

import com.zsw.base.repository.jpa.CustomRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * @author ZhangShaowei on 2017/9/12 15:57
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.*", repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class JpaConfiguration {


    /**
     * @return
     */
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }

}
