package com.zsw.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2021/6/7 10:30
 */
@Service
public class AccountService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void reduce(String userId, int money) {
        this.jdbcTemplate.update("update account_tbl set money = money - ? where user_id = ?", money, userId);
    }

}
