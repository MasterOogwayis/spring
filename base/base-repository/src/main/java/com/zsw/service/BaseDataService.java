package com.zsw.service;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author Shaowei Zhang on 2019/3/17 18:53
 **/
public interface BaseDataService<T, E extends Serializable> {


    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    void delete(E id);

    /**
     * @param entity
     */
    void delete(T entity);

    /**
     * save
     *
     * @param record
     * @return
     */
    T save(T record);

    /**
     * saveOrUpdate
     *
     * @param record
     * @return
     */
    T saveOrUpdate(T record);


    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    T get(E id);


    List<T> findAll(T record);

    /**
     * 分页条件查询
     *
     * @param t
     * @param page
     * @param limit
     * @return
     */
    Page<T> findAll(T t, int page, int limit);

    Page<T> findAll(T t, int page, int limit, String sourName, String sortType);

}
