//package com.zsw.persistence.repository.base;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.NoRepositoryBean;
//
//import java.io.Serializable;
//
///**
// * @author ZhangShaowei on 2021/11/17 17:11
// */
//@NoRepositoryBean
//public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
//
//    void delete(ID id);
//
//    /**
//     * 请使用 Optional 返回的实现
//     *
//     * @param id ID
//     * @return
//     */
//    T get(ID id);
//
//    T saveOrUpdate(T t);
//
//}
