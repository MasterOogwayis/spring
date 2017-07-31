package com.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ZhangShaowei on 2017/7/11 17:28
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * @return Docket
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.boot"))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("这是一个测试用例")
                .termsOfServiceUrl("https://user.qzone.qq.com/499504777/infocenter?_t_=0.367268413243794")
                .contact(
                        new Contact(
                                "zsw",
                                "https://user.qzone.qq.com/499504777/infocenter?_t_=0.367268413243794",
                                "ShaoweiZhang2016@outlook.com"))
                .version("1.0")
                .build();
    }

}
