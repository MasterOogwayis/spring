package com.zsw.controller;

import com.zsw.persistence.repository.IcbcOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author ZhangShaowei on 2019/11/14 13:48
 **/
@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    private IcbcOrderRepository icbcOrderRepository;

    @GetMapping("get")
    public Object get(@RequestParam("id") Long id) {
        return this.icbcOrderRepository.get(id);
    }

    @GetMapping("list")
    public Object list() {
        return this.icbcOrderRepository.list("from IcbcOrder");
    }

    @GetMapping("query")
    public Object query(@RequestParam("amount") Long amount) {
        return this.icbcOrderRepository.t(amount);
    }

    @GetMapping("find")
    public Object find() {
        return this.icbcOrderRepository.findByNamedParam("from IcbcOrder where amount = :amount", Collections.singletonMap("amount", 100L));
    }

}
