package com.zsw.api.product;

import com.zsw.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/10/12 11:25
 */
@RestController
@RequestMapping("product")
public class ProductController {

    /**
     *
     */
    @Autowired
    private ProductService productService;


    /**
     * @param num num
     * @return
     */



}
