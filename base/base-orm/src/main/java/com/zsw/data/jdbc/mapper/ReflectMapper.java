package com.zsw.data.jdbc.mapper;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.ReflectionUtils;
import sun.reflect.misc.ReflectUtil;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Shaowei Zhang on 2019/4/20 20:58
 **/
@AllArgsConstructor
public class ReflectMapper<T> implements RowMapper<T> {

    private Class<T> clazz;

    @SneakyThrows
    @Override
    @SuppressWarnings("unchecked")
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        T t = (T) ReflectUtil.newInstance(this.clazz);
//            Field[] fields = clazz.getDeclaredFields();
//            if (ArrayUtils.isNotEmpty(fields)) {
//                for (Field field : fields) {
//                    Object object = rs.getObject(field.getName(), field.getType());
//                    ReflectionUtils.makeAccessible(field);
//                    field.set(t, object);
//                }
//            }
        int columnCount = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String name = rs.getMetaData().getColumnName(i);
            Field declaredField = this.clazz.getDeclaredField(name);
            if (Objects.nonNull(declaredField)) {
                ReflectionUtils.makeAccessible(declaredField);
                declaredField.set(t, rs.getObject(i));
            }
        }
        return t;

    }

//    @SuppressWarnings("unchecked")
//    private Class<T> getTClass() {
//        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//    }

}
