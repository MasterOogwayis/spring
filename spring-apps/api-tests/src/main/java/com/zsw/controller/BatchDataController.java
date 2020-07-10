package com.zsw.controller;

import com.zsw.mapper.ProductMapper;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.concurrent.CountDownLatch;

/**
 * @author ZhangShaowei on 2020/7/8 15:30
 */
@Slf4j
@RequestMapping("batch")
@RestController
public class BatchDataController {

    /**
     *
     */
    @Autowired
    private ProductMapper mapper;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    private static final String[] STR = new String[]{"Earth", "Mars", "Moon", "Sun"};

    private static final int INDEX = ~(-1 << 2);


    /**
     * 数量
     *
     * @return
     */
    @GetMapping
    public Object insert() throws Exception {
        log.info("{}", "asd");
        long timer = System.currentTimeMillis();
        int threads = Runtime.getRuntime().availableProcessors() * 2 + 1;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            this.executor.execute(() -> {
                try {
                    @Cleanup var connection = BatchDataController.this.dataSource.getConnection();
                    connection.setAutoCommit(false);
                    @Cleanup var preparedStatement = connection.prepareStatement("insert into t_product (productName, quantity, price, address) values(?, ?, ?, ?)");
                    int batchSize = 10000;
                    for (int j = 0; j < 300000; j++) {
                        preparedStatement.setString(1, j + "");
                        preparedStatement.setInt(2, j % 999);
                        preparedStatement.setDouble(3, j);
                        preparedStatement.setString(4, STR[j & INDEX]);
                        preparedStatement.addBatch();
                        if (j % batchSize == 0) {
                            preparedStatement.executeBatch();
                            connection.commit();
                        }
                    }
                    preparedStatement.executeBatch();
                    connection.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        return System.currentTimeMillis() - timer;
    }

}
