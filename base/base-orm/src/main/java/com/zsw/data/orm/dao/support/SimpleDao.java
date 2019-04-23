package com.zsw.data.orm.dao.support;

import com.zsw.data.orm.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangShaowei on 2019/4/22 15:40
 **/
public class SimpleDao<T, ID extends Serializable> implements BaseDao<T, ID> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EntityOperation<T> eo;

    public SimpleDao() {
        this.eo = new EntityOperation<>(this.getTClass());
    }

    @Override
    public T get(ID id) {
        String sql = "select * from " + this.eo.getTable() + " where " + this.eo.getPkName() + "=?";
        List<T> ts = this.jdbcTemplate.queryForList(sql, this.eo.getClazz(), id);
        return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, this.eo.getRowMapper());
    }

    @Override
    public ID save(T t) {
        return null;
    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(T t) {

    }

    @Override
    public List<T> findAll() {
        String sql = "select * from " + this.eo.getTable();
        return this.jdbcTemplate.query(sql, this.eo.getRowMapper());
    }


    @SuppressWarnings("unchecked")
    protected Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
