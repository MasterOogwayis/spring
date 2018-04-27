package com.zsw.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * FreeMakerConfiguration
 *
 * @author ZhangShaowei on 2018/4/26 14:03
 **/
@Configuration
public class FreeMakerConfiguration {

    /**
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setViewClass(org.springframework.web.servlet.view.freemarker.FreeMarkerView.class);
        resolver.setRequestContextAttribute("request");
        //resolver.setExposeSpringMacroHelpers(true);
        resolver.setExposeRequestAttributes(true);
        resolver.setExposeSessionAttributes(true);
        resolver.setSuffix(".html");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }

//    @Bean
//    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
//        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
//        factory.setTemplateLoaderPath("classpath:/templates/");
//        factory.setDefaultEncoding("UTF-8");
//        factory.setPreferFileSystemAccess(false);
//        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
//        freemarker.template.Configuration configuration = factory.createConfiguration();
//        configuration.setClassicCompatible(true);
//        result.setConfiguration(configuration);
//        Properties settings = new Properties();
//        settings.put("template_update_delay", "0");
//        settings.put("default_encoding", "UTF-8");
//        settings.put("number_format", "0.######");
//        settings.put("classic_compatible", true);
//        settings.put("template_exception_handler", "ignore");
//        result.setFreemarkerSettings(settings);
//        return result;
//    }

}
