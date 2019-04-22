package com.zsw.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * BaseRepository
 *
 * @author ZhangShaowei on 2017/9/8 14:48
 * @param <T> class
 * @param <ID> Long
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * @return EntityManager
     */
    EntityManager getEntityManager();

    /**
     * 将对象从 t 会话中拆离
     * @param t T
     */
    void evict(T t);

    /**
     * 获得一个新的 EntityManager
     * @return EntityManager
     */
    EntityManager getNewEntityManager();

    /**
     * saveOrUpdate
     *
     * @param t T
     * @return t
     */
    T saveOrUpdate(T t);

}
