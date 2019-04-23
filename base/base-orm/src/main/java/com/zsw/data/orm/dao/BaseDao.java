package com.zsw.data.orm.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangShaowei on 2019/4/22 14:55
 **/
public interface BaseDao<T, ID extends Serializable> {

    T get(ID id);

    ID save(T t);

    void update(T t);

    void delete(T t);

    List<T> findAll();

}
