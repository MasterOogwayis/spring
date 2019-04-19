package com.zsw.service;

import com.zsw.persistence.Customer;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Shaowei Zhang on 2019/4/19 22:16
 **/
@Slf4j
@Service
public class CustomerService {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;


    @SneakyThrows
    public Object get(Long id) {
        @Cleanup Connection connection = this.template.getDataSource().getConnection();
        String sql = "select * from t_customer where id = ?";
        @Cleanup PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        @Cleanup ResultSet resultSet = statement.executeQuery();
        List<Object[]> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(new Object[]{resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getTimestamp(4)});
        }
        return data;
    }


    public List<Customer> findAll() {
        String sql = "select * from t_customer";
        RowMapper<Customer> rowMapper = new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                return Customer.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .age(resultSet.getInt("age"))
                        .createDate(resultSet.getTimestamp("createDate")).build();
            }
        };
        return this.namedParameterJdbcOperations.query(sql, rowMapper);
    }


}
