package com.zsw.base.repository.impl;

import ch.qos.logback.classic.Logger;
import com.zsw.base.repository.CustomRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @param <T> T
 * @author ZhangShaowei on 2017/9/12 15:41
 */
public class CustomRepositoryImpl<T> implements CustomRepository<T> {

    /**
     *
     */
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    /**
     *
     */
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    /**
     * @return Class
     */
    @SuppressWarnings("unchecked")
    public Class<T> getClazz() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    /**
     * 描述：删除实体对象
     *
     * @param entity 实体对象
     */
    @Override
    public void delete(T entity) {
        this.entityManager.remove(entity);
    }

    /**
     * @param entities List<T>
     */
    @Override
    public void deleteAll(final Collection<T> entities) {
        Assert.notEmpty(entities, "entities must not be null");
        for (T t : entities) {
            this.entityManager.remove(t);
        }
    }

    /**
     * @param id Long
     * @return T
     */
    @Override
    public T get(final Long id) {
        return this.entityManager.getReference(this.getClazz(), id);
    }

    /**
     * 非代理对象
     *
     * @param id
     * @return
     */
    @Override
    public T findOne(final Long id) {
        return this.entityManager.find(this.getClazz(), id);
    }

    /**
     * @param hqlStr hsql
     * @return list
     */
    @Override
    public List<T> find(final String hqlStr) {
        return this.find(hqlStr, -1, -1);
    }

    /**
     * @param hqlStr     hql
     * @param maxResults maxSize
     * @return list
     */
    @Override
    public List<T> find(final String hqlStr, final int maxResults) {
        return this.find(hqlStr, 0, maxResults);
    }

    /**
     * @param hqlStr      hql
     * @param firstResult first
     * @param maxResults  maxSize
     * @return list
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> find(final String hqlStr, final int firstResult, final int maxResults) {
        Query query = this.entityManager.createNativeQuery(hqlStr);
        if (maxResults > -1) {
            query.setMaxResults(maxResults);
        }
        if (firstResult > -1) {
            query.setFirstResult(firstResult);
        }
        return query.getResultList();
    }

    /**
     * @param hqlStr hql
     * @param values params
     * @return list
     */
    @Override
    public List<T> findByParam(final String hqlStr, final Object... values) {
        return this.findByParam(hqlStr, -1, -1, values);
    }

    /**
     * @param hqlStr     hql
     * @param maxResults maxSize
     * @param values     params
     * @return list
     */
    @Override
    public List<T> findByParam(final String hqlStr, final int maxResults, final Object... values) {
        return findByParam(hqlStr, 0, maxResults, values);
    }

    /**
     * @param hqlStr      hql
     * @param firstResult first
     * @param maxResults  maxSize
     * @param values      params
     * @return list
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByParam(
            final String hqlStr, final int firstResult, final int maxResults, final Object... values) {
        Query query = this.entityManager.createQuery(hqlStr);
        if (firstResult > 0) {
            query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query.setMaxResults(maxResults);
        }
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        return query.getResultList();
    }

    /**
     * @param hqlStr     hql
     * @param paramName  param name
     * @param paramValue param value
     * @return list
     */
    @Override
    public List<T> findByNamedParam(final String hqlStr, final String paramName, final Object paramValue) {
        return findByNamedParam(hqlStr, -1, -1, paramName, paramValue);
    }

    /**
     * @param hqlStr     hql
     * @param maxResults 最大查询的条数，大于0的整数，否则忽略
     * @param paramName  绑定:name的name
     * @param paramValue 绑定:name的value
     * @return list
     */
    @Override
    public List<T> findByNamedParam(
            final String hqlStr, final int maxResults, final String paramName, final Object paramValue) {
        return findByNamedParam(hqlStr, 0, maxResults, paramName, paramValue);
    }

    /**
     * @param hqlStr      hql
     * @param firstResult 开始查询的行数，大于或等于0的整数，否则忽略
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param paramName   绑定:name的name
     * @param paramValue  绑定:name的value
     * @return list
     */
    @Override
    public List<T> findByNamedParam(
            final String hqlStr, final int firstResult, final int maxResults,
            final String paramName, final Object paramValue) {
        return findByNamedParam(
                hqlStr, firstResult, maxResults, new String[]{paramName}, new Object[]{paramValue});
    }

    /**
     * @param hqlStr      hql
     * @param paramNames  绑定:name的names
     * @param paramValues 绑定:name的values
     * @return list
     */
    @Override
    public List<T> findByNamedParam(
            final String hqlStr, final String[] paramNames, final Object[] paramValues) {
        return findByNamedParam(hqlStr, -1, -1, paramNames, paramValues);
    }

    /**
     * @param hqlStr      hql
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param paramNames  绑定:name的names
     * @param paramValues 绑定:name的values
     * @return list
     */
    @Override
    public List<T> findByNamedParam(
            final String hqlStr, final int maxResults, final String[] paramNames, final Object[] paramValues) {
        return findByNamedParam(hqlStr, 0, maxResults, paramNames, paramValues);
    }

    /**
     * @param hqlStr      hql
     * @param firstResult 开始查询的行数，大于或等于0的整数，否则忽略
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param paramNames  绑定:name的names
     * @param paramValues 绑定:name的values
     * @return list
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByNamedParam(
            final String hqlStr, final int firstResult, final int maxResults,
            final String[] paramNames, final Object[] paramValues) {
        Query query = this.entityManager.createQuery(hqlStr);
        if (firstResult > 0) {
            query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query.setMaxResults(maxResults);
        }
        if (paramValues != null) {
            for (int i = 0; i < paramValues.length; i++) {
                if (paramValues[i] instanceof Collection) {
                    query.setParameter(paramNames[i], (Collection<?>) paramValues[i]);
                } else if (paramValues[i] instanceof Object[]) {
                    query.setParameter(paramNames[i], (Object[]) paramValues[i]);
                } else {
                    query.setParameter(paramNames[i], paramValues[i]);
                }
            }
        }
        return query.getResultList();
    }

    /**
     * @param hqlStr      hql
     * @param namedParams name，value映射
     * @return list
     */
    @Override
    public List<T> findByNamedParam(final String hqlStr, final Map<String, Object> namedParams) {
        return findByNamedParam(hqlStr, -1, -1, namedParams);
    }

    /**
     * @param hqlStr      hql
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param namedParams name，value映射
     * @return list
     */
    @Override
    public List<T> findByNamedParam(
            final String hqlStr, final int maxResults, final Map<String, Object> namedParams) {
        return findByNamedParam(hqlStr, 0, maxResults, namedParams);
    }

    /**
     * @param hqlStr      hql
     * @param firstResult 开始查询的行数，大于或等于0的整数，否则忽略
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param namedParams name，value映射
     * @return list
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByNamedParam(
            final String hqlStr, final int firstResult, final int maxResults, final Map<String, Object> namedParams) {
        Query query = this.entityManager.createQuery(hqlStr);
        if (firstResult > 0) {
            query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query.setMaxResults(maxResults);
        }
        if (namedParams != null) {
            for (Map.Entry<String, Object> entry : namedParams.entrySet()) {
                if (entry.getValue() instanceof Collection) {
                    query.setParameter(entry.getKey(), (Collection<?>) entry.getValue());
                } else if (entry.getValue() instanceof Object[]) {
                    query.setParameter(entry.getKey(), (Object[]) entry.getValue());
                } else {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
        }
        return query.getResultList();
    }
}
