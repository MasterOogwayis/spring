package com.zsw.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    T get(ID id);

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


    List<T> find(String hqlStr);

    List<T> find(String hqlStr, int maxResults);

    List<T> find(String hqlStr, int firstResult, int maxResults);

    List<T> findByParam(String hqlStr, Object... values);

    List<T> findByParam(String hqlStr, int maxResults, Object... values);

    List<T> findByParam(String hqlStr, int firstResult, int maxResults, Object... values);

    List<T> findByNamedParam(String hqlStr, Map<String, Object> namedParams);

    List<T> findByNamedParam(String hqlStr, int maxResults, Map<String, Object> namedParams);

    List<T> findByNamedParam(String hqlStr, int firstResult, int maxResults, Map<String, Object> namedParams);

}
