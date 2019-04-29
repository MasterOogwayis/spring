package com.zsw.mybatis.typehandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zsw.persistence.entity.Comment;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ZhangShaowei on 2019/4/28 11:46
 **/
public class CommentTypeHandler extends BaseTypeHandler<Comment> {

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Comment parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, this.gson.toJson(parameter));
    }

    @Override
    public Comment getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        if (StringUtils.hasText(string)) {
            return this.gson.fromJson(string, Comment.class);
        }
        return null;
    }

    @Override
    public Comment getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        if (StringUtils.hasText(string)) {
            return this.gson.fromJson(string, Comment.class);
        }
        return null;
    }

    @Override
    public Comment getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        if (StringUtils.hasText(string)) {
            return this.gson.fromJson(string, Comment.class);
        }
        return null;
    }
}
