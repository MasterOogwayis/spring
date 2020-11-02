package com.zsw.controller;

import com.zsw.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZhangShaowei on 2020/11/2 10:53
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public List<String> getNames() {
        return productMapper.findNames();
    }

}
