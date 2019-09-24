package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2019/9/19 13:46
 **/
@SpringBootApplication
public class TestApplication {

//    @Autowired
//    @Qualifier
//    private Map<String, String> maps = Collections.emptyMap();

    public static void main(String[] args) {
//        new SpringApplicationBuilder().main(TestApplication.class)
//                .web(WebApplicationType.NONE)
//                .build()
//                .run(args);
        SpringApplication.run(TestApplication.class, args);
    }

//    @Bean
//    public ApplicationRunner applicationRunner() {
//        return args -> {
//            System.out.println(maps);
//        };
//    }
//
//    @Bean
//    @Qualifier
//    public String a() {
//        return "String-a";
//    }
//
//    @Bean
//    public String b() {
//        return "String-b";
//    }
//
//    @Bean
//    @Qualifier
//    public String c() {
//        return "String-c";
//    }


}
