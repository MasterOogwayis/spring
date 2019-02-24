package com;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * test
 *
 * @author Shaowei Zhang on 2019/2/21 21:46
 **/
@SpringBootApplication
public class TestWeb {

    public static void main(String[] args) {
        SpringApplication.run(TestWeb.class, args);
    }



    @RequestMapping("test")
    @RestController
    class TestController {

        @GetMapping
        public DataDto test(@ModelAttribute DataDto data) {
            return data;
        }


    }

    @Data
    @NoArgsConstructor
    private class DataDto {

        private Long id;

        private String name;

        private String address;

        private Integer age;

    }


}
