package com.zsw.metrics.micrometer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.util.StringUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * spring boot 2.x
 *
 * 停机配置，shutdown 后立即向 eureka 注销服务，然后等待所有请求已处理再关机，超出等待时间则强行关机
 *
 * endpoints.shutdown.enabled=true
 *
 * 停机方法:
 *   1. 请求 /shutdown 接口，可能涉及到权限问题
 *   2. 使用 JMX 调用 ShutdownEndpoint.shutdown() 方法  （推荐）
 *   3. spring.application.admin.enabled=true SpringApplicationAdminMXBean 提供的 shutdown，这是个阻塞方法，会导致最终强行关机
 *
 * @author zsw on 2019/4/18 11:24
 **/
@Slf4j
@Configuration
public class ShutdownConfigurationV2 {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer() {
//        return container -> {
//            if (container instanceof TomcatEmbeddedServletContainerFactory) {
//                ((TomcatEmbeddedServletContainerFactory) container).addConnectorCustomizers(gracefulShutdown());
//            }
//
//        };
        return factory -> factory.addConnectorCustomizers(gracefulShutdown());
    }

//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        tomcat.addConnectorCustomizers(gracefulShutdown());
//        return tomcat;
//    }

    @Bean
    public GracefulShutdown gracefulShutdown() {
        return new GracefulShutdown();
    }


    public static class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

        private static final String shutdownTimeoutKey = "endpoints.shutdown.waitedTime";

        private volatile long shutdownTimeout = 30L;

        private volatile Connector connector;

        @Override
        public void customize(Connector connector) {
            this.connector = connector;
        }

        @Override
        public void onApplicationEvent(ContextClosedEvent event) {
            String propertyTime = event.getApplicationContext().getEnvironment().getProperty(shutdownTimeoutKey);
            if (StringUtils.hasText(propertyTime)) {
                try {
                    this.shutdownTimeout = Long.parseLong(propertyTime);
                } catch (Exception e) {
                    // doNothing...
                }
            }
            this.connector.pause();
            Executor executor = this.connector.getProtocolHandler().getExecutor();
            if (executor instanceof ThreadPoolExecutor) {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                try {
                    threadPoolExecutor.shutdown();
                    boolean waiting = false;
                    for (int i = 0; i < shutdownTimeout; i++) {
                        // 等待所有请求处理完
                        if (threadPoolExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
                            log.info("All requests have been processed, shutdown gracefully...");
                            waiting = true;
                            break;
                        }
                        log.warn("Waiting for all requests to be processed, forceful shutdown in {}s", this.shutdownTimeout - i);
                    }
                    if (!waiting) {
                        log.warn("Tomcat thread pool did not shut down gracefully within {} seconds. Proceeding with forceful shutdown", this.shutdownTimeout);
                        threadPoolExecutor.shutdownNow();
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    threadPoolExecutor.shutdownNow();
                }
            }
        }
    }


}
