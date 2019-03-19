package com;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ZhangShaowei on 2019/3/13 14:23
 **/
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "com.zsw.test.properties")
public class TestProperties {

    private Map<String, Merchant> merchants;


    @Data
    public static class Merchant {
        private String name;

        private String address;
    }

}
