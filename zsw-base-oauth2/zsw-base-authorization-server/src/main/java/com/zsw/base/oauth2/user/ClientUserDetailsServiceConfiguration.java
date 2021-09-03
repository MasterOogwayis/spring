package com.zsw.base.oauth2.user;

import com.zsw.base.oauth2.ClientUserDetailsService;
import com.zsw.base.oauth2.user.impl.CompositeClientUserDetailsServiceImpl;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2020/7/2 16:27
 */
@Configuration
@ConditionalOnBean(ClientUserDetailsService.class)
public class ClientUserDetailsServiceConfiguration {


    /**
     * @param servicesProvider ClientUserDetailsService
     * @return CompositeClientUserDetailsService
     */
    @Primary
    @Bean
    public ClientUserDetailsService clientUserDetailsService(
            ObjectProvider<ClientUserDetailsService> servicesProvider) {
        return servicesProvider.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), CompositeClientUserDetailsServiceImpl::new));
    }


}
