package com.zsw.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2021/6/7 13:23
 */
@Service
public class StorageService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void deduct(String commodityCode, int count) {
        jdbcTemplate.update("update storage_tbl set count = count - ? where commodity_code = ?",
                count, commodityCode);
    }

}
