package com;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ZhangShaowei on 2019/3/27 16:18
 **/
@Configuration
@Import(TestService.class)
public class TestConfiguration {
}
