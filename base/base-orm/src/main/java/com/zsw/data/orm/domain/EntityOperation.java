package com.zsw.data.orm.domain;


import org.springframework.jdbc.core.RowMapper;

/**
 * TODO  通过 getter setter 来操作值
 *
 * @author Shaowei Zhang on 2019/4/22 20:05
 **/
public interface EntityOperation<T> {

    RowMapper<T> getRowMapper();

    /**
     * get
     *
     * @return
     */
    String createGetSql();

    /**
     * get
     *
     * @return
     */
    String createDeleteSql();

    /**
     * update
     * <p>
     * 根据 实例创建 update sql， 必须包含主键
     *
     * @param t
     * @return
     */
    PropertiesHandler createUpdateSql(T t);

    /**
     * 现在不处理自增id 数据库自己处理  TODO
     * insert into table(name) values(value);
     *
     * @param t
     * @return
     */
    PropertiesHandler createSaveSql(T t);

    PropertiesHandler merge(T t);

    String findAll();

    /**
     * 查询
     *
     * @param t
     * @return
     */
    PropertiesHandler findByParams(T t);


}
