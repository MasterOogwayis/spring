package com.zsw.repository;

import com.querydsl.core.dml.InsertClause;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 *
 *
 * @author ZhangShaowei on 2021/11/18 9:50
 */
@NoRepositoryBean
public interface QueryDslRepository<T, ID extends Serializable> {

    EntityManager getEntityManager();

    JPAQuery<T> queryDsl();

    InsertClause<?> insertClause(EntityPath<?> entityPath);

    UpdateClause<?> updateClause(EntityPath<?> entityPath);

}
