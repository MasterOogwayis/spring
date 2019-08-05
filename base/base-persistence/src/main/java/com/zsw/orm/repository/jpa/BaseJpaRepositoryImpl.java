package com.zsw.orm.repository.jpa;

import com.zsw.orm.repository.BaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangShaowei on 2017/9/8 14:43
 */

public class BaseJpaRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    /**
     *
     */
    private final EntityManager entityManager;
    /**
     *
     */
    private final Class<T> domainClass;

    /**
     * @param entityInformation
     * @param entityManager
     */
    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.domainClass = entityInformation.getJavaType();
    }

    /**
     * 父类没有不带参数的构造方法，这里手动构造父类
     *
     * @param domainClass   T
     * @param entityManager EntityManager
     */
    public BaseJpaRepositoryImpl(final Class<T> domainClass, final EntityManager entityManager) {
        super(domainClass, entityManager);
        this.domainClass = domainClass;
        this.entityManager = entityManager;
    }

    /**
     * @return EntityManager
     */
    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public T get(ID id) {
        return this.findById(id).orElse(null);
    }

    /**
     * @param t T
     */
    @Override
    public void evict(T t) {
        this.entityManager.getEntityManagerFactory().getCache()
                .evict(domainClass, t);
    }

    /**
     * @return EntityManager
     */
    @Override
    public EntityManager getNewEntityManager() {
        return entityManager.getEntityManagerFactory().createEntityManager();
    }


    /**
     * 描述：通过hql执行查询
     *
     * @param hqlStr hql
     * @return 对象集合
     * @author lurf
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
     * @author lurf
     * @version 2.0.0 2012-9-11
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
     * @author lurf
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
     * @author lurf
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
     * @author lurf
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
     * @author lurf
     * @version 2.0.0 2012-9-11
     * @since 2.0.0
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByParam(String hqlStr, int firstResult, int maxResults, Object... values) {
        Query query = entityManager.createQuery(hqlStr);
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
     * @author lurf
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
     * @author lurf
     * @version 2.0.0 2012-9-18
     * @since 2.0.0
     */
    @Override
    public List<T> findByNamedParam(String hqlStr, int maxResults, Map<String, Object> namedParams) {
        return findByNamedParam(hqlStr, 0, maxResults, namedParams);
    }

    /**
     * 描述：通过参数绑定:name方式查询<br>
     * <span style="color: red;">&lt;勿忘国耻，纪念9·18&gt<br>
     * &lt;钓岛要收复，反日要理性&gt;</span>
     *
     * @param hqlStr      hql
     * @param firstResult 开始查询的行数，大于或等于0的整数，否则忽略
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param namedParams name，value映射
     * @return 对象集合
     * @author lurf
     * @version 2.0.0 2012-9-18
     * @since 2.0.0
     */
    @Override
    public List<T> findByNamedParam(String hqlStr, int firstResult, int maxResults, Map<String, Object> namedParams) {
        {
            Query query = entityManager.createQuery(hqlStr);
            if (firstResult > 0) {
                query.setFirstResult(firstResult);
            }
            if (maxResults > 0) {
                query.setMaxResults(maxResults);
            }
            if (namedParams != null) {
                for (String paramName : namedParams.keySet()) {
                    Object paramValue = namedParams.get(paramName);
                    if (paramValue instanceof Collection) {
                        query.setParameter(paramName, (Collection<?>) paramValue);
                    } else if (paramValue instanceof Object[]) {
                        query.setParameter(paramName, (Object[]) paramValue);
                    } else {
                        query.setParameter(paramName, paramValue);
                    }
                }
            }
            return query.getResultList();

        }
    }

}
