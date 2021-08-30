package com.zsw.api;

import com.zsw.persistence.entity.Customer;
import com.zsw.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZhangShaowei on 2021/8/26 10:45
 */
@RequestMapping("customer")
@RestController
public class CustomerApi {

    @Autowired
    private CustomerService customerService;

    @GetMapping("find")
    public Page<Customer> findAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return this.customerService.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "id"));
    }

    @GetMapping("findByPhone")
    public List<Customer> findByPhone(@RequestParam("phone") String phone) {
        return this.customerService.findByPhone(phone);
    }


    @GetMapping("init")
    public Object init(@RequestParam("num") Integer num) {
        this.customerService.init(num);
        return "success";
    }

    @GetMapping("init0")
    public Object init0(@RequestParam("num") Integer num) {
        this.customerService.init0(num);
        return "success";
    }

}
