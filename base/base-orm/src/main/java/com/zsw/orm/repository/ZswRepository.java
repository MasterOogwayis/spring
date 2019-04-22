package com.zsw.orm.repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Shaowei Zhang on 2019/4/20 22:07
 **/
public interface ZswRepository<T, ID extends Serializable> {



    T get(ID id);

    List<T> findAll();

    void delete(T t);

    void saveOrUpdate(T t);


}
