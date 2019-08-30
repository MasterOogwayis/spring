package com.zsw.orm.client.config;

/**
 * 由于同一个服务可能存在多个 客户端会报错
 *
 * FeignClientsRegistrar#243
 * <p>
 * java.lang.IllegalStateException: Cannot define alias 'basic-dataFeignClient' for name '*':
 * It is already registered for name '*'.
 *
 * @author ZhangShaowei on 2019/8/2 14:05
 **/
//@Configuration
public class FeignClientConfig {

//    /**
//     * FeignClientFactoryBean is set in the factory class when the builder property is set, the source code can be seen
//     */
//    @Autowired
//    private FeignContext feignContext;
//
//    /**
//     * By injecting Eureka instance objects, you don't have to manually specify the url, just specify the service name.
//     */
//    @Autowired
//    private EurekaClient eurekaClient;
//
//    private <T> T create(Class<T> clazz, String serverId) {
//        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka(serverId, false);
//        return Feign.builder()
//                .encoder(feignContext.getInstance(serverId, feign.codec.Encoder.class))
//                .decoder(feignContext.getInstance(serverId, feign.codec.Decoder.class))
//                .contract(feignContext.getInstance(serverId, Contract.class))
//                .target(clazz, nextServerFromEureka.getHomePageUrl());
//
//    }


}
