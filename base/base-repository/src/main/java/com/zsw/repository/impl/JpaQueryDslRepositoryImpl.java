package com.zsw.repository.impl;

import com.querydsl.core.dml.InsertClause;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

    private JPAQueryFactory factory;


    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }


    @Override
    public JPAQuery<T> queryDsl() {
        return new JPAQuery<>(getEntityManager());
    }

    @Override
    public InsertClause<?> insertClause(EntityPath<?> entityPath) {
        return this.factory.insert(entityPath);
    }

    @Override
    public UpdateClause<?> updateClause(EntityPath<?> entityPath) {
        return this.factory.update(entityPath);
    }
}
