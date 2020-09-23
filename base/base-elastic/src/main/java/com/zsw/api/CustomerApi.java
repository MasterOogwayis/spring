package com.zsw.api;

import com.zsw.pojo.dto.CustomerDto;
import com.zsw.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/9/23 15:14
 */
@RequestMapping("customer")
@RestController
public class CustomerApi {

    @Autowired
    private CustomerService customerService;



    @PostMapping("save")
    public Object save(@RequestBody CustomerDto customerDto) {
        return this.customerService.save(customerDto);
    }

    @GetMapping("search")
    public Object search(@RequestParam("name") String name) {

    }

}
