package com.test.demo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author ZhangShaowei on 2019/6/21 13:27
 **/
@TestConfiguration
public class JunitConfiguration {

    @Bean
    public DoSomeThingService doSomeThingService() {
        DoSomeThingService mock = mock(DoSomeThingService.class);

        when(mock.get(any())).thenReturn("asd");

        return mock;

    }

}
