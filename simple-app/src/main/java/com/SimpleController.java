package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangShaowei on 2019/4/26 10:46
 **/
@RequestMapping("product")
@RestController
public class SimpleController {

    @Autowired
    ProductService productService;

    @GetMapping("get")
    public Object get(@RequestParam("id") Long id) {
        return this.productService.get(id);
    }

    @PostMapping("save")
    public Object save(@RequestBody Product product) {
        return this.productService.save(product);
    }


    @GetMapping("getCustom")
    public Object getCustom(@RequestParam("id") Long id) {
        return this.productService.getCustom(id);
    }

    @PostMapping("saveCustom")
    public Object saveCustom(@RequestBody Product product) {
        return this.productService.saveCustom(product);
    }



}
