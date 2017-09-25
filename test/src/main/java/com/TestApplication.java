package com;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/9/11 14:27
 */
//@RestController
//@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TestApplication.class, args);
        Long timer = System.currentTimeMillis();
        long i = 0L;
        for (long j = 0L; j < 2500000000L; j++) {
            i++;
        }
        System.out.println(System.currentTimeMillis() - timer);

    }

    /**
     * @return
     */
    @PostMapping("test")
    public String get() {
        return "success";
    }

}
