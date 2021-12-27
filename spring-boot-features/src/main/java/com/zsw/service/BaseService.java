//package com.zsw.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
///**
// * @author ZhangShaowei on 2021/8/26 10:35
// */
//public class BaseService<R extends JpaRepository<T, ID>, T, ID> {
//
//    @Autowired
//    protected R repository;
//
//    public T getById(ID id) {
//        return this.repository.getById(id);
//    }
//
//    public Page<T> findAll(Pageable pageable) {
//        return this.repository.findAll(pageable);
//    }
//
//    public List<T> findAll() {
//        return this.repository.findAll();
//    }
//
//
//}
