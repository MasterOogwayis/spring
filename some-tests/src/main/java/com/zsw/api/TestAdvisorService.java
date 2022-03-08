package com.zsw.api;

import com.zsw.aop.AHello;
import com.zsw.aop.Hello;
import org.springframework.stereotype.Service;

/**
 * @author Shaowei Zhang on 2022/3/7 20:39
 */
@Service
public class TestAdvisorService {

    public TestAdvisorService() {

        System.out.println(123);

    }

    @Hello
    public String hello() {
        return "Hello World";
    }

    @AHello
    public String ahello() {
        return "Hello World";
    }

}
