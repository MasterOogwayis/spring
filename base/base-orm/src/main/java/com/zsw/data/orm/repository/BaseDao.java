package com.zsw.data.orm.repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Shaowei Zhang on 2019/4/20 22:07
 **/
public interface BaseDao<T, ID extends Serializable> {



    T get(ID id);

    int save(T t);

    void delete(ID id);

    void update(T t);

    List<T> findAll();

    List<T> find(T t);

    void saveOrUpdate(T t);

}
