package com.zsw.repository.impl;

import com.zsw.repository.BaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author ZhangShaowei on 2017/9/8 14:43
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
    public T get(ID id) {
        return this.findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) {
        this.findById(id).ifPresent(this::delete);
    }

    @Override
    public void saveOrUpdate(T t) {
        this.save(t);
    }



}
