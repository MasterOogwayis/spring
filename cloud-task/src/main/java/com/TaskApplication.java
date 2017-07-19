package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ZhangShaowei on 2017/4/26 15:22
 */
@SpringBootApplication
@EnableTask
public class TaskApplication {


    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }


    /**
     * @return
     */
    @Bean
    public CommandLineRunner commandLineRunner(){
        return new HelloWorldCommandLineRunner();
    }


    public static class HelloWorldCommandLineRunner implements CommandLineRunner{

        @Override
        public void run(String... strings) throws Exception {
            System.out.println("Hello World!");
        }
    }


}
