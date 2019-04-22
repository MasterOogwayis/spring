package com.zsw.data.orm.repository.support;

import com.zsw.data.orm.domain.EntityOperation;
import com.zsw.data.orm.domain.PropertiesHandler;
import com.zsw.data.orm.repository.BaseDao;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Shaowei Zhang on 2019/4/20 22:10
 **/
public class SimpleDao<T, ID extends Serializable> implements BaseDao<T, ID> {

    private EntityOperation<T> eo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SimpleDao() {
        this.eo = new EntityOperation<>(this.getTClass());
    }

    @Override
    public T get(ID id) {
        return this.jdbcTemplate.queryForObject(this.eo.createGetSql(), this.eo.getRowMapper(), id);
    }

    @Override
    public int save(T t) {
        PropertiesHandler handler = this.eo.createSaveSql(t);
        return this.jdbcTemplate.update(handler.getSql(), handler.getProperties().values().toArray());
    }

    @Override
    public List<T> findAll() {
        String sql = this.eo.findAllSql(null);
        return this.jdbcTemplate.query(sql, this.eo.getRowMapper());
    }


    @Override
    @SneakyThrows
    public void delete(ID id) {
        String deleteSql = this.eo.createDeleteSql();
        this.jdbcTemplate.update(deleteSql, id);
    }

    @Override
    public void update(T t) {
        PropertiesHandler handler = this.eo.createUpdateSql(t);
        this.jdbcTemplate.update(handler.getSql(), handler.getProperties().values().toArray());
    }

    @Override
    public void saveOrUpdate(T t) {
        PropertiesHandler handler = this.eo.merge(t);
        this.jdbcTemplate.update(handler.getSql(), handler.getProperties().values().toArray());
    }


    /**
     * let the subclass to support
     *
     * @return
     */
    protected JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    @SuppressWarnings("unchecked")
    private Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
