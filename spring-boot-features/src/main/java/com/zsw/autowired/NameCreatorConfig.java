package com.zsw.autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author ZhangShaowei on 2021/12/27 9:34
 */
@Configuration
public class NameCreatorConfig {

    @Bean
    public NameHandler nameHandler(@Value("#{@stringNameCreator.names()}") List<String> names) {
        return new NameHandler(names);
    }


}
