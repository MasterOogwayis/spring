//package com.zsw.persistence.repository.base;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import java.io.Serializable;
//
///**
// * @author ZhangShaowei on 2021/11/17 16:22
// */
//public interface BaseCustomRepository<T, ID extends Serializable> {
//    /**
//     * @return
//     */
//    EntityManager getEntityManager();
//
//    /**
//     * @param hql hql
//     * @return TypedQuery
//     */
//    TypedQuery<T> createQuery(String hql);
//
//}
