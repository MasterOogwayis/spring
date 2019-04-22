package com.zsw.data.orm.domain;

import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO  通过 getter setter 来操作值
 *
 * @author Shaowei Zhang on 2019/4/22 20:05
 **/
@Data
public class EntityOperation<T> {

    /**
     * 实体对象 class
     */
    private Class<T> clazz;

    /**
     * 表名
     */
    private String table;

    /**
     * 主键 name
     */
    private String pkName;

    private Field pfField;

    private RowMapper<T> rowMapper;

    private Map<String, Field> mappings;


    public EntityOperation(Class<T> clazz) {
        this.clazz = clazz;
        this.mappings = new HashMap<>();
        this.readClass();
        this.rowMapper = createRowMapper();
    }


    @SneakyThrows
    private void readClass() {
        if (!this.clazz.isAnnotationPresent(Entity.class)) {
            throw new Exception("对象" + this.clazz.getName() + " 没有 @Entity 注解无法被解析");
        }
        if (!this.clazz.isAnnotationPresent(Table.class)) {
            throw new Exception("对象" + this.clazz.getName() + " 没有 @Table 注解无法被解析");
        }

        if ((this.table = this.clazz.getAnnotation(Table.class).name()) == "") {
            throw new Exception("对象" + this.clazz.getName() + " @Table 找不到可用表名");
        }

        Field[] fields = this.clazz.getDeclaredFields();

        if (fields.length == 0) {
            throw new Exception(this.clazz.getName() + " 找不到可用属性");
        }

        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                // 排除 static,final修饰的属性
                continue;
            }
            String columnName;
            if (field.isAnnotationPresent(Column.class)) {
                columnName = field.getAnnotation(Column.class).name();
                if (columnName.equals("")) {
                    // 没有注解
                    columnName = field.getName();
                }
                if (field.isAnnotationPresent(Id.class)) {
                    this.pkName = columnName;
                    this.pfField = field;
                }

            } else {
                // 忽略没有被@Column注解的属性
                continue;
            }
            this.mappings.put(columnName, field);
        }

        if (this.mappings.size() == 0) {
            throw new Exception(this.clazz.getName() + " 找不到可用属性");
        }
    }


    private RowMapper<T> createRowMapper() {
        return new RowMapper<T>() {
            @Override
            @SneakyThrows
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                T t = clazz.newInstance();
                ResultSetMetaData metaData = rs.getMetaData();
                int count = metaData.getColumnCount();
                String name;
                Object value;
                for (int i = 1; i <= count; i++) {
                    name = metaData.getColumnName(i);
                    Field field = mappings.get(name);
                    if (field == null) {
                        continue;
                    }
                    value = rs.getObject(i);

                    field.setAccessible(true);
                    field.set(t, value);
                }
                return t;
            }
        };

    }

    /**
     * get
     *
     * @return
     */
    public String createGetSql() {
        return "select * from " + this.table + " where " + this.pkName + "=?";
    }

    /**
     * get
     *
     * @return
     */
    public String createDeleteSql() {
        return "delete from " + this.table + " where " + this.pkName + "=?";
    }

    /**
     * update
     * <p>
     * 根据 实例创建 update sql， 必须包含主键
     *
     * @param t
     * @return
     */
    public PropertiesHandler createUpdateSql(T t) {
        Map<String, Object> objectMap = this.readProperties(t);
        // 将主键取出来
        Object id = objectMap.remove(this.pkName);
        StringBuilder sql = new StringBuilder("update ");
        sql.append(this.table).append(" set ");
        String collect = objectMap.keySet().stream().collect(Collectors.joining("=?,", "", "=?"));
        sql.append(collect);
        sql.append(" where ").append(this.pkName).append("=?");
        // 保证设值参数的顺序 最后再把 id 放入 map
        objectMap.put(this.pkName, id);
        return new PropertiesHandler(sql.toString(), objectMap);
    }

    /**
     * 现在不处理自增id 数据库自己处理  TODO
     * insert into table(name) values(value);
     *
     * @param t
     * @return
     */
    public PropertiesHandler createSaveSql(T t) {
        Map<String, Object> objectMap = this.readProperties(t);
        // 新增忽略主键
        objectMap.remove(this.pkName);
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(this.table);
        // table(name,name,name)
        String tableColumns = objectMap.keySet().stream().collect(Collectors.joining(",", "(", ")"));
        sql.append(tableColumns).append(" ");
        String values = objectMap.keySet().stream().map(name -> "?").collect(Collectors.joining(",", "values(", ")"));
        sql.append(values);
        return new PropertiesHandler(sql.toString(), objectMap);
    }

    @SneakyThrows
    public PropertiesHandler merge(T t) {
        if (Objects.isNull(this.pfField.get(t))) {
            // insert
            return this.createSaveSql(t);
        } else {
            // update
            return this.createUpdateSql(t);
        }
    }

    public String findAllSql(Set<String> params) {
        String sql = "select * from " + this.table;
        if (!CollectionUtils.isEmpty(params)) {
            StringBuilder sb = new StringBuilder(" where ");
            for (String param : params) {
                sb.append(param).append("=? ");
            }
            sql += sb.toString();
        }
        return sql;
    }

    /**
     * 将对象转换为 map 属性，便于设值
     *
     * @param t
     * @return
     */
    public Map<String, Object> readProperties(T t) {
        Map<String, Object> map = new LinkedHashMap<>();
        this.mappings.forEach((key, field) -> {
            try {
                field.setAccessible(true);
                map.put(key, field.get(t));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return map;
    }


}
