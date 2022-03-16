package com.zsw.repository;

import com.querydsl.jpa.impl.JPAProvider;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * @author ZhangShaowei on 2021/11/19 17:10
 */
@Configuration
public class QuerydlsFactoryConfig {


    /**
     * JPQLTemplates templates
     *
     * @param entityManager
     * @return
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(JPAProvider.getTemplates(entityManager), entityManager);
    }

}
