//package com.zsw.persistence.repository.base.impl;
//
//import com.zsw.persistence.repository.base.BaseCustomRepository;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ResolvableType;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import java.io.Serializable;
//
///**
// * @author ZhangShaowei on 2021/11/17 16:22
// */
//public class BaseCustomRepositoryImpl<T, ID extends Serializable>
//        implements BaseCustomRepository<T, ID>, InitializingBean {
//
//    @Autowired
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    /**
//     *
//     */
//    protected Class<T> domainClass;
//
//    @Override
//    public EntityManager getEntityManager() {
//        return entityManager;
//    }
//
//
//    @Override
//    public TypedQuery<T> createQuery(String hql) {
//        return this.entityManager.createQuery(hql, this.domainClass);
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public void afterPropertiesSet() {
//        this.domainClass = (Class<T>) ResolvableType.forClass(getClass()).getSuperType().resolveGeneric(0);
//    }
//
//}
