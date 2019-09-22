package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Administrator on 2019/9/16 21:37
 **/
@PropertySource("classpath:jdbc.properties")
@Configuration
public class PropertySourceConfiguration {
}
