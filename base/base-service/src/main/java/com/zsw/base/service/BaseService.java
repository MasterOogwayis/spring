package com.zsw.base.service;

import com.zsw.base.utils.JacksonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/9/8 16:24
 */

public interface BaseService<T, E extends Serializable> {


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
