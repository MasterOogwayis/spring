package com.zsw.repository;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * @author ZhangShaowei on 2021/11/19 17:10
 */
@Configuration
public class QuerydlsFactoryConfig {


    @Bean
    public JPAQueryFactory jpaQueryFactory(JPQLTemplates templates, EntityManager entityManager) {
        return new JPAQueryFactory(templates, entityManager);
    }

}
