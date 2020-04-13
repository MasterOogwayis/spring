package com.zsw.orm.repository.impl;

import com.zsw.orm.repository.BaseCustomRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * 需要自定义 方法 或 使用 sql的实现用这个
 * <p>
 * 注：能使用 hql 的方法都能使用 注解方式实现
 *
 * @author zsw 2019年7月29日09:33:40
 */
public class BaseCustomRepositoryImpl<T, ID extends Serializable> implements BaseCustomRepository<T, ID>, InitializingBean {

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     *
     */
    protected Class<T> domainClass;


    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * 描述：通过hql执行查询
     *
     * @param hqlStr hql
     * @return 对象集合
     * @author zsw
     * @version 2.0.0 2012-9-10
     * @since 2.0.0
     */
    @Override
    public List<T> find(String hqlStr) {
        return find(hqlStr, -1, -1);
    }

    /**
     * 描述：通过hql执行查询
     *
     * @param hqlStr     hql
     * @param maxResults 最大查询的条数，大于0的整数，否则忽略
     * @return 对象集合
     * @author zsw
     * @since 2.0.0
     */
    @Override
    public List<T> find(String hqlStr, int maxResults) {
        return find(hqlStr, 0, maxResults);
    }

    /**
     * 描述：通过hql执行查询
     *
     * @param hqlStr      hql
     * @param firstResult 开始查询的行数，大于或等于0的整数，否则忽略
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @return 对象集合
     * @author zsw
     * @version 2.0.0 2012-9-11
     * @since 2.0.0
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> find(String hqlStr, int firstResult, int maxResults) {
        Query query = entityManager.createQuery(hqlStr);
        if (maxResults > -1) {
            query.setMaxResults(maxResults);
        }
        if (firstResult > -1) {
            query.setFirstResult(firstResult);
        }
        return query.getResultList();
    }

    /**
     * 描述：通过hql执行查询，通过参数绑定“?”的方式查询
     *
     * @param hqlStr hql
     * @param values 绑定“?”的值
     * @return 对象集合
     * @author zsw
     * @version 2.0.0 2012-9-10
     * @since 2.0.0
     */
    @Override
    public List<T> findByParam(String hqlStr, Object... values) {
        return findByParam(hqlStr, -1, -1, values);
    }

    /**
     * 描述：通过hql执行查询，通过参数绑定“?”的方式查询
     *
     * @param hqlStr     hql
     * @param maxResults 最大查询的条数，大于0的整数，否则忽略
     * @param values     绑定“?”的值
     * @return 对象集合
     * @author zsw
     * @version 2.0.0 2012-9-11
     * @since 2.0.0
     */
    @Override
    public List<T> findByParam(String hqlStr, int maxResults, Object... values) {
        return findByParam(hqlStr, 0, maxResults, values);
    }

    /**
     * 描述：通过hql执行查询，通过参数绑定“?”的方式查询
     *
     * @param hqlStr      hql
     * @param firstResult 开始查询的行数，大于或等于0的整数，否则忽略
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param values      绑定“?”的值
     * @return 对象集合
     * @author zsw
     * @version 2.0.0 2012-9-11
     * @since 2.0.0
     */
    @Override
    public List<T> findByParam(String hqlStr, int firstResult, int maxResults, Object... values) {
        TypedQuery<T> query = entityManager.createQuery(hqlStr, this.domainClass);
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
     * 描述：通过参数绑定:name方式查询
     *
     * @param hqlStr      hql
     * @param namedParams name，value映射
     * @return 对象集合
     * @author zsw
     * @version 2.0.0 2012-9-18
     * @since 2.0.0
     */
    @Override
    public List<T> findByNamedParam(String hqlStr, Map<String, Object> namedParams) {
        return findByNamedParam(hqlStr, -1, -1, namedParams);
    }

    /**
     * 描述：通过参数绑定:name方式查询
     *
     * @param hqlStr      hql
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param namedParams name，value映射
     * @return 对象集合
     * @author zsw
     * @version 2.0.0 2012-9-18
     * @since 2.0.0
     */
    @Override
    public List<T> findByNamedParam(String hqlStr, int maxResults, Map<String, Object> namedParams) {
        return findByNamedParam(hqlStr, 0, maxResults, namedParams);
    }

    /**
     * 描述：通过参数绑定:name方式查询<br>
     *
     * @param hqlStr      hql
     * @param firstResult 开始查询的行数，大于或等于0的整数，否则忽略
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param namedParams name，value映射
     * @return 对象集合
     * @author zsw
     * @version 2.0.0 2012-9-18
     * @since 2.0.0
     */
    @Override
    public List<T> findByNamedParam(String hqlStr, int firstResult, int maxResults, Map<String, Object> namedParams) {
        TypedQuery<T> query = entityManager.createQuery(hqlStr, this.domainClass);
        if (firstResult > 0) {
            query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query.setMaxResults(maxResults);
        }
        if (null != namedParams && !namedParams.isEmpty()) {
            namedParams.forEach(query::setParameter);
        }
        return query.getResultList();

    }


    @Override
    public List<T> list(String hql) {
        return this.createQuery(hql).getResultList();
    }

    @Override
    public TypedQuery<T> createQuery(String hql) {
        return this.entityManager.createQuery(hql, this.domainClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        this.domainClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
