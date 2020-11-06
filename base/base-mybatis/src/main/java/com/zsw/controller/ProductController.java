package com.zsw.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zsw.persistence.entity.Product;
import com.zsw.service.ProductService;
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
    private ProductService productService;

    @GetMapping
    public List<String> getNames() {
        return this.productService.listObjs(Wrappers.<Product>lambdaQuery().select(Product::getProductName), Object::toString);
    }

}
