package com.zsw.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.zsw.repository.QueryDslRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * @author ZhangShaowei on 2021/11/18 9:50
 */
public class JpaQueryDslRepositoryImpl<T, ID extends Serializable> implements QueryDslRepository<T, ID> {

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


    @Override
    public JPAQuery<T> queryDsl() {
        return new JPAQuery<>(getEntityManager());
    }
}
