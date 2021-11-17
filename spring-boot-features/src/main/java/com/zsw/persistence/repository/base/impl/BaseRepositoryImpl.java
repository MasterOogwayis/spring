package com.zsw.persistence.repository.base.impl;

import com.zsw.persistence.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author ZhangShaowei on 2021/11/17 17:10
 */
public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {


    /**
     * @param entityInformation
     * @param entityManager
     */
    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public void delete(ID id) {
        this.findById(id).ifPresent(this::delete);
    }

    @Deprecated
    @Override
    public T get(ID id) {
        return this.findById(id).orElse(null);
    }

    @Override
    public T saveOrUpdate(T t) {
        return this.save(t);
    }
}
