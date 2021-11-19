package com.zsw.repository;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author ZhangShaowei on 2021/11/18 9:50
 */
@NoRepositoryBean
public interface QueryDslRepository<T, ID extends Serializable> {

    EntityManager getEntityManager();

    JPAQuery<T> queryDsl();

}
