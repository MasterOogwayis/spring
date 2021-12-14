package com.zsw.base.oauth2.userinfo;

import com.zsw.base.oauth2.userinfo.impl.ResourceSessionUserServiceImpl;
import com.zsw.user.SessionUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShaowei on 2020/9/9 15:50
 */
@Configuration
public class DefaultUserServiceConfiguration {

    @Bean
    public SessionUserService sessionUserService() {
        return new ResourceSessionUserServiceImpl();
    }

}
