package com.zsw.service;

import com.zsw.persistence.entity.TestUser;
import com.zsw.persistence.repository.TestUserRepository;
import com.zsw.pojo.NamesOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author ZhangShaowei on 2021/8/26 10:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TestUserService extends BaseService<TestUserRepository, TestUser, Long> {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<NamesOnly> findByFirstName(String firstName) {
        List<NamesOnly> list = super.repository.findByFirstName(firstName);
        return list;
    }

    public List<Object[]> findByAndSort(String name) {
        return super.repository.findByAndSort(name, PageRequest.of(0, 20, DESC, "id"));
    }

    public List<TestUser> findByPhone(String phone) {
        return super.repository.findByPhone(phone);
    }


    public void init(Integer num) {
        long maxId = this.getMaxId();
        List<TestUser> list = new ArrayList<>(500);
        for (int i = 0; i < num; i++) {
            long j = i + maxId;
            TestUser build = TestUser.builder()
                    .address("Earth" + j)
                    .age((int) (j & 17))
                    .phone(String.valueOf(j))
                    .name("name" + j)
                    .firstName("first" + j)
                    .lastName("last" + j)
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
        String sql = "insert into t_user(`name`,firstName, lastName, address, age, phone, creator)" +
                " values(?, ?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                long j = maxId + i;
                ps.setString(1, "name" + j);
                ps.setString(2, "first" + j);
                ps.setString(3, "last" + j);
                ps.setString(4, "Earth" + j);
                ps.setInt(5, (int) (j & 17));
                ps.setString(6, String.valueOf(j));
                ps.setString(7, "sys");
            }

            @Override
            public int getBatchSize() {
                return num;
            }
        });
    }

    public long getMaxId() {
        PageRequest pageRequest = PageRequest.of(0, 1, DESC, "id");
//        Page<TestUser> page = super.repository.findAll(pageRequest);
        return super.repository.findAllByPage(pageRequest)
                .stream().findAny().map(TestUser::getId).orElse(0L);
    }

}
