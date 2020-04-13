package com.zsw;

import com.zsw.controller.TestBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ZhangShaowei on 2019/11/15 13:53
 **/
@Configuration
@Import(TestBean.class)
public class TestConfiguration {
}
