package com.zsw.repository;

import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @param <T>
 * @param <ID>
 * @author zsw 2019年7月29日09:33:16
 */
@NoRepositoryBean
public interface BaseCustomRepository<T, ID extends Serializable> {

    EntityManager getEntityManager();

    /**
     * @param hqlStr
     * @return
     * @deprecated 使用 @Query + Pageable
     */
    @Deprecated
    List<T> find(String hqlStr);

    /**
     * @param hqlStr
     * @param maxResults
     * @return
     * @deprecated 使用 @Query + Pageable
     */
    @Deprecated
    List<T> find(String hqlStr, int maxResults);

    /**
     * @param hqlStr
     * @param firstResult
     * @param maxResults
     * @return
     * @deprecated 使用 @Query + Pageable
     */
    @Deprecated
    List<T> find(String hqlStr, int firstResult, int maxResults);

    /**
     * @param hqlStr
     * @param values
     * @return
     * @deprecated 使用 @Query + Pageable
     */
    @Deprecated
    List<T> findByParam(String hqlStr, Object... values);

    /**
     * @param hqlStr
     * @param maxResults
     * @param values
     * @return
     * @deprecated 使用 @Query + Pageable
     */
    @Deprecated
    List<T> findByParam(String hqlStr, int maxResults, Object... values);

    /**
     * @param hqlStr
     * @param firstResult
     * @param maxResults
     * @param values
     * @return
     * @deprecated 使用 findByNamedParam
     */
    @Deprecated
    List<T> findByParam(String hqlStr, int firstResult, int maxResults, Object... values);

    List<T> findByNamedParam(String hqlStr, Map<String, Object> namedParams);

    List<T> findByNamedParam(String hqlStr, int maxResults, Map<String, Object> namedParams);

    List<T> findByNamedParam(String hqlStr, int firstResult, int maxResults, Map<String, Object> namedParams);

    List<T> list(String hql);

    TypedQuery<T> createQuery(String hql);

}
