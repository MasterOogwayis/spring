package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/4/26 10:46
 **/
@Slf4j
@RestController
@RequestMapping("test")
public class SimpleController {

//    @Autowired
//    private CustomerRepository customerRepository;

    @GetMapping("count")
    public Object count(@RequestParam("age") Integer age) {
//        Customer zsw = this.customerRepository.getByName("zsw");
//        System.out.println(zsw);
//        return this.customerRepository.countByAge(age);
        return null;
    }


}
