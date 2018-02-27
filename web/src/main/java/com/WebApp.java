package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a test demo.
 *
 * @author ZhangShaowei on 2018/2/11 13:40
 **/
@SpringBootApplication
public class WebApp {

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }


    @RestController
    public static class Demo {

        @GetMapping("hello")
        public String get(){
            return this.getName();
        }

        private String getName(){
            return "Mr Zhang";
        }

    }

}
