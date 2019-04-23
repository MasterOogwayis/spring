package com.zsw.data.orm.dao.support;

import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ZhangShaowei on 2019/4/22 15:56
 **/
@Getter
public class EntityOperation<T> {

    private Map<String, Field> mappings;

    private String pkName;

    private String table;

    private RowMapper<T> rowMapper;

    private Class<T> clazz;

    public EntityOperation(Class<T> clazz) {
        this.clazz = clazz;
        this.mappings = new HashMap<>();
        this.validate();
        this.rowMapper = new RowMapper<T>() {
            @Override
            @SneakyThrows
            public T mapRow(ResultSet resultSet, int i) throws SQLException {
                T t = clazz.newInstance();
                int count = resultSet.getMetaData().getColumnCount();
                String name;
                for (int j = 1; j <= count; j++) {
                    name = resultSet.getMetaData().getColumnName(j);
                    Object value = resultSet.getObject(j);
                    Field field = mappings.get(name);
                    if (Objects.nonNull(field)) {
                        field.setAccessible(true);
                        field.set(t, value);
                    }

                }
                return t;
            }
        };
    }

    /**
     * 验证实体类注解
     */
    private void validate() {
        if (!this.clazz.isAnnotationPresent(Entity.class)) {
            throw new RuntimeException("实体类 " + this.clazz.getName() + " 没有 @Entity 注解无法被映射");
        }
        if (!this.clazz.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("实体类 " + this.clazz.getName() + " 找不到 @Table 注解");
        }

        Table table = this.clazz.getAnnotation(Table.class);
        if ((this.table = table.name()).equals("")) {
            throw new RuntimeException(this.clazz.getName() + " @Table 没找到可用表");
        }


        Field[] fields = this.clazz.getDeclaredFields();
        Assert.notEmpty(fields, "实体类 " + this.clazz.getName() + " 未找到定义的属性");

        for (Field field : fields) {
            // 不处理 static, final 修饰的属性
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            String columnName;
            if (field.isAnnotationPresent(Column.class)) {
                columnName = field.getAnnotation(Column.class).name();
                if (Objects.equals(columnName, "")) {
                    // 默认使用属性名
                    columnName = field.getName();
                }
                if (field.isAnnotationPresent(Id.class)) {
                    // @Id 是主键
                    this.pkName = columnName;
                }
            } else {
                // 不处理没有 @Column 注解的字段
                continue;
            }
            this.mappings.put(columnName, field);
        }

        if (Objects.isNull(this.pkName)) {
            throw new RuntimeException(this.clazz.getName() + " 未找到主键 @Id");
        }
    }


}
