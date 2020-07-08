package com.zsw.controller;

import com.zsw.client.TcClient;
import com.zsw.entity.ProductInfoIntercity;
import com.zsw.mapper.ProductInfoIntercityMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2020/7/8 9:37
 */
@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    private ProductInfoIntercityMapper productInfoIntercityMapper;

    @Autowired
    private TcClient tcClient;

    @Autowired
    private ThreadPoolTaskExecutor executor;


    @GetMapping
    @SneakyThrows
    public Object getAllProductCode() {
        List<ProductInfoIntercity> lists = this.productInfoIntercityMapper.findAllProductCode();
        for (int i = 0; i < 100; i++) {
            lists.stream().map(ProductInfoIntercity::getProductCode).forEach(this::sendTo);
            TimeUnit.MILLISECONDS.sleep(100);
        }
        return "success";

    }


    private void sendTo(String lineCode) {
        for (int i = 0; i < 10; i++) {
            int j = i;
            this.executor.execute(() -> {
                try {
                    TestController.this.tcClient.simpleListTimeScopeByLineCode(lineCode + j);
                } catch (Exception e) {
                    e.printStackTrace();
                    // do nothing
                }
            });
        }
    }


}
