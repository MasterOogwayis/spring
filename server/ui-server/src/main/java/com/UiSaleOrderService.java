package com;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZhangShaowei on 2017/12/18 15:44
 */
@Service
@Configuration
public class UiSaleOrderService {

    /**
     *
     */
    @Autowired
    private RestTemplate restTemplate;


    /**
     * @param id id
     * @return
     */
    @HystrixCommand(fallbackMethod = "fail")
    public SaleOrderDto get(final Long id) {
        return this.restTemplate.getForEntity(
                "http://api-server/saleorder/get?id=" + id, SaleOrderDto.class).getBody();
    }

    /**
     * @param id id
     * @return
     */
    public SaleOrderDto fail(final Long id) {
        return new SaleOrderDto();
    }


    /**
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
