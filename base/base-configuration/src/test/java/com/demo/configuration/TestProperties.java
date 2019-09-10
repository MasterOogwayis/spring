package com.demo.configuration;


import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

/**
 * @author Administrator on 2019/9/8 13:23
 **/
public class TestProperties {

    public static void main(String[] args) throws Exception {

//        Properties properties = new Properties();
//        properties.setProperty("name", "Shaowei Zhang");
//        properties.setProperty("age", "18");
//        properties.setProperty("address", "Earth");
//        properties.storeToXML(System.out, "This is a demo.");

        Properties properties = new Properties();
        properties.load(new ClassPathResource("1.properties").getInputStream());
        System.out.println(properties.getProperty("name", "null"));
        System.out.println(properties.getProperty("other", "null"));

//        System.out.println(System.getenv("JAVA_HOME"));
//        System.out.println(System.getProperty("user.home"));
//        System.out.println(Integer.getInteger("user.age", 11)); // -Duser.age=12
//        System.out.println(Long.getLong("user.age", 12L));
//        System.out.println(Boolean.getBoolean("user.young"));
//        System.err.println(System.getProperty("user.age", "0"));




    }


}
