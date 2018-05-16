package com.config;

import com.zsw.persistence.user.bean.User;
import com.zsw.service.security.SecurityService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * DevTestConfiguration
 *
 * @author ZhangShaowei on 2018/5/15 13:54
 **/
@TestConfiguration
public class DevTestConfiguration {


    @Bean
    public SecurityService securityService() {
        SecurityService securityService = mock(SecurityService.class);
        User user = new User();
        user.setUsername("mock user");
        when(securityService.get(1L)).thenReturn(user);
        return securityService;
    }


}
