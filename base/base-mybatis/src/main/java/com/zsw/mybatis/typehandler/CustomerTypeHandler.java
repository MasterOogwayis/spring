package com.zsw.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ZhangShaowei on 2019/4/25 16:25
 **/
@MappedTypes(String.class)
// #{name,jdbcType=VARCHAR}
@MappedJdbcTypes(value = JdbcType.VARCHAR, includeNullJdbcType = true)
public class CustomerTypeHandler implements TypeHandler<String> {


    private static final String AUTHOR = "Author";
    private static final String NAME = "zsw";

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter);
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String name = rs.getString(columnName);
        if (NAME.equalsIgnoreCase(name)) {
            return AUTHOR;
        }
        return name;
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        String name = rs.getString(columnIndex);
        if (NAME.equalsIgnoreCase(name)) {
            return AUTHOR;
        }
        return name;
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String name = cs.getString(columnIndex);
        if (NAME.equalsIgnoreCase(name)) {
            return AUTHOR;
        }
        return name;
    }
}
