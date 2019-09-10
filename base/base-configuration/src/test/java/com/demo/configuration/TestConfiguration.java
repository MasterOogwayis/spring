package com.demo.configuration;

import org.apache.commons.configuration.*;

import java.util.Arrays;

/**
 * @author Administrator on 2019/9/8 15:05
 **/
public class TestConfiguration {

    public static void main(String[] args) {
        Configuration systemConfiguration = new SystemConfiguration();
        Configuration osConfiguration = new EnvironmentConfiguration();
        Configuration inMemory = new BaseConfiguration();
        inMemory.setProperty("user.age", "30");
        inMemory.addProperty("user.age", "30");

        Configuration configuration = new CompositeConfiguration(Arrays.asList(inMemory, systemConfiguration, osConfiguration));
        System.err.println(configuration.getString("user.age"));

        //language=JSON
        String json = "";

    }

}
