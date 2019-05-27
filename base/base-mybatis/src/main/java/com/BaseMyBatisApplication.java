package com;

import com.zsw.persistence.dao.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/4/25 10:04
 **/
@SpringBootApplication
public class BaseMyBatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseMyBatisApplication.class, args);
    }


    @RequestMapping("test")
    @RestController
    class TestMyBatisController {

        @Autowired
        CustomerMapper customerMapper;

        @GetMapping
        public Object test() {
            return customerMapper.findAll();
        }

    }

}
