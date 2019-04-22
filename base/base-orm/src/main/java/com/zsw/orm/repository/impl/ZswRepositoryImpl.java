package com.zsw.orm.repository.impl;

import com.zsw.commons.utils.GenericsUtils;
import com.zsw.orm.repository.ZswRepository;
import com.zsw.orm.jdbc.mapper.ReflectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Shaowei Zhang on 2019/4/20 22:10
 **/
public class ZswRepositoryImpl<T, ID extends Serializable> implements ZswRepository<T, ID> {

    private RowMapper<T> rowMapper;

    private Class<T> clazz;

    private String primaryKeyName;

    private String table;

    @SuppressWarnings("unchecked")
    public ZswRepositoryImpl() {
        this.clazz = GenericsUtils.getSuperClassGenricType(this.getClass());
        this.rowMapper = new ReflectMapper<>(this.clazz);
        if (!this.clazz.isAnnotationPresent(Entity.class)) {
            throw new RuntimeException("缺少 @Entity 的实体对象无法被映射");
        }
        if (!this.clazz.isAnnotationPresent(Table.class)
                || (this.table = this.clazz.getAnnotation(Table.class).name()) == "") {
            throw new RuntimeException("找不到 " + this.clazz.getName() + " 对应的 table");
        }
        Field[] fields = this.clazz.getDeclaredFields();
        List<Field> columns = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                columns.add(field);
                if (field.isAnnotationPresent(Id.class) && Objects.nonNull(this.primaryKeyName)) {
                    throw new RuntimeException("主键 @Id 重复 " + field.getName());
                } else if (field.isAnnotationPresent(Id.class) && Objects.isNull(this.primaryKeyName)) {
                    this.primaryKeyName = field.getName();
                }
            }
        }
        if (Objects.isNull(this.primaryKeyName)) {
            throw new RuntimeException("对象 " + this.clazz.getName() + " 找不到主键");
        }

    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public T get(ID id) {
        String sql = "select * from " + this.table + " where " + this.primaryKeyName + "=?";
        return this.jdbcTemplate.queryForObject(sql, this.rowMapper, id);
    }

    @Override
    public List<T> findAll() {
        String sql = "select * from " + this.table;
        return this.jdbcTemplate.query(sql, this.rowMapper);
    }

    @Override
    public void delete(T t) {

    }

    @Override
    public void saveOrUpdate(T t) {

    }


    /**
     * let the subclass to impl
     *
     * @return
     */
    protected JdbcTemplate getJdbcTemplate() {
        return null;
    }
}
