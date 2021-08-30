package com.zsw.service;

import com.zsw.persistence.entity.Customer;
import com.zsw.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/8/26 10:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerService extends BaseService<CustomerRepository, Customer, Long> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> findByPhone(String phone) {
        return super.repository.findByPhone(phone);
    }


    public void init(Integer num) {
        long maxId = this.getMaxId();
        List<Customer> list = new ArrayList<>(500);
        for (int i = 0; i < num; i++) {
            long j = i + maxId;
            Customer build = Customer.builder()
                    .address("Earth" + j)
                    .age((int) (j & 17))
                    .phone(String.valueOf(j))
                    .name("name" + j)
                    .build();
            list.add(build);
            if ((i + 1) % 500 == 0) {
                super.repository.saveAllAndFlush(list);
                list.clear();
            }
        }
        if (!list.isEmpty()) {
            super.repository.saveAllAndFlush(list);
        }
    }


    public void init0(Integer num) {
        long maxId = this.getMaxId();
        String sql = "insert into t_customer(`name`, address, age, phone, creator)" +
                " values(?, ?, ?, ?, ?)";
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                long j = maxId + i;
                ps.setString(1, "name" + j);
                ps.setString(2, "Earth" + j);
                ps.setInt(3, (int) (j & 17));
                ps.setString(4, String.valueOf(j));
                ps.setString(5, "sys");
            }

            @Override
            public int getBatchSize() {
                return num;
            }
        });
    }

    public long getMaxId() {
        PageRequest pageRequest = PageRequest.of(0, 1, Sort.Direction.DESC, "id");
//        Page<Customer> page = super.repository.findAll(pageRequest);
        return super.repository.findAllByPage(pageRequest)
                .stream().findAny().map(Customer::getId).orElse(0L);
    }

}
