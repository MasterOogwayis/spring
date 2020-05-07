package com;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsw.persistence.dao.ProductMapper;
import com.zsw.persistence.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        ProductMapper mapper;

        @GetMapping("find")
        public Object test(@RequestParam("page") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
            PageHelper.startPage(pageNum, pageSize, true);
            List<Product> list = mapper.findAll();
            return new PageInfo<>(list);
        }

        @GetMapping("get")
        public Object test(@RequestParam("id") Long id) {
            return mapper.selectByPrimaryKey(id);
        }

    }

}
