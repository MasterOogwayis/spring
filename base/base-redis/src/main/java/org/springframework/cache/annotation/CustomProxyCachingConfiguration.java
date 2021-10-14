package org.springframework.cache.annotation;

/**
 * @author ZhangShaowei on 2019/6/18 17:40
 * @see org.springframework.cache.annotation.ProxyCachingConfiguration
 **/
//@Configuration
//@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class CustomProxyCachingConfiguration { //extends ProxyCachingConfiguration {

//    @Bean(name = CacheManagementConfigUtils.CACHE_ADVISOR_BEAN_NAME)
//    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//    public BeanFactoryCacheOperationSourceAdvisor cacheAdvisor() {
//        BeanFactoryCacheOperationSourceAdvisor advisor = new BeanFactoryCacheOperationSourceAdvisor();
//        advisor.setCacheOperationSource(cacheOperationSource());
//        advisor.setAdvice(cacheInterceptorCustom());
//        if (this.enableCaching != null) {
//            advisor.setOrder(this.enableCaching.<Integer>getNumber("order"));
//        }
//        return advisor;
//    }
//
//    @Bean
//    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//    public CacheOperationSource cacheOperationSource() {
//        return new AnnotationCacheOperationSource(new CustomSpringCacheAnnotationParser());
//    }
//
//    @Bean
//    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//    public CustomCacheInterceptor cacheInterceptorCustom() {
//        CustomCacheInterceptor interceptor = new CustomCacheInterceptor();
//        interceptor.configure(this.errorHandler, this.keyGenerator, this.cacheResolver, this.cacheManager);
//        interceptor.setCacheOperationSource(cacheOperationSource());
//        return interceptor;
//    }

}
