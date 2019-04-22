package com.zsw.orm.repository.jpa;

import com.zsw.orm.repository.BaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author ZhangShaowei on 2017/9/8 14:43
 */

public class BaseJpaRepository<T, ID extends Serializable>
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
    public BaseJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
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
    public BaseJpaRepository(final Class<T> domainClass, final EntityManager entityManager) {
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
     * @param t T
     * @return
     */
    @Override
    public T saveOrUpdate(T t) {
        return entityManager.merge(t);
    }
}
